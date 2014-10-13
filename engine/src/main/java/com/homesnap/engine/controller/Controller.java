package com.homesnap.engine.controller;

/*
 * #%L
 * HomeSnapEngine
 * %%
 * Copyright (C) 2011 - 2014 A. de Giuli
 * %%
 * This file is part of HomeSnap done by Arnaud de Giuli (arnaud.degiuli(at)free.fr)
 *     helped by Olivier Driesbach (olivier.driesbach(at)gmail.com).
 * 
 *     HomeSnap is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     HomeSnap is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with HomeSnap. If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import java.io.IOException;
import java.io.Serializable;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.JSONException;
import org.json.JSONObject;

import com.homesnap.engine.JsonSerializable;
import com.homesnap.engine.configuration.Property;
import com.homesnap.engine.connector.Command;
import com.homesnap.engine.connector.Command.Type;
import com.homesnap.engine.connector.CommandListener;
import com.homesnap.engine.connector.CommandResult;
import com.homesnap.engine.connector.CommandResultStatus;
import com.homesnap.engine.connector.Commander;
import com.homesnap.engine.connector.DefaultCommandResult;
import com.homesnap.engine.controller.properties.StateSection;
import com.homesnap.engine.controller.properties.StatesReader;
import com.homesnap.engine.controller.what.State;
import com.homesnap.engine.controller.what.StateName;
import com.homesnap.engine.controller.what.StateValue;
import com.homesnap.engine.controller.what.StateValueType;
import com.homesnap.engine.controller.where.Where;
import com.homesnap.engine.controller.who.Who;

public abstract class Controller implements JsonSerializable, Serializable {

	/** serial uid */
	private static final long serialVersionUID = 1L;
	
	public static final String STATES_EXTENSION = ".states";
	
	private boolean waitingResult = false;
	protected Where where; // Represent the address of the controller
	private String title; // string representing the controller
	private String description; // string describing the controller
	protected transient Commander server;
	private List<ControllerChangeListener> controllerChangeListenerList = new ArrayList<ControllerChangeListener>();
	private LabelList labelList = new LabelList(this);
	
	/** List of all state names with their current values of the controller. This map repesents the complete status of the controller. */
	private Map<StateName, StateValue> stateList = new HashMap<StateName, StateValue>();
	/** The state section of the configuration file which contains all states names that the controller can implements. */
	private StateSection stateTypes;
	/** Cache of all controller classes with their state types */
	private static Map<Class<?>, StateSection> classTypes = new Hashtable<Class<?>, StateSection>();

	public static final String JSON_TITLE = "title";
	public static final String JSON_STATES = "states";
	public static final String JSON_WHERE = "where";
	public static final String JSON_WHO = "who";
	public static final String JSON_DESCRIPTION = "description";
	
	/**
	 * Constructor.
	 */
	protected Controller() {
		initStateTypes(getClass());
	}
	
	/**
	 * Initializes the state names with their class types in order to prevent from a wrong assigment when the {@link #set(StateName, StateValue)} method is called.
	 */
	private void initStateTypes(Class<?> clazz) {
		// Check if the controller class is known
		stateTypes = classTypes.get(clazz);
		if (stateTypes == null) {
			Class<?> superClass = clazz.getSuperclass();
			while (!Controller.class.equals(superClass)) {
				initStateTypes(superClass);
				break;
			}
			// Search the ".states" resource which defines the state types of the controller
			String pkgName = clazz.getPackage().getName().replace('.', '/');
			URL url = clazz.getClassLoader().getResource(pkgName +"/"+ clazz.getSimpleName() +".states");
			if (url == null) {
				throw new RuntimeException("Unable to find states definition file for "+ clazz.getName());
			}
			// Load the definition file
			StatesReader reader = new StatesReader();
			try {
				reader.load(url.openStream());
			} catch (IOException e) {
				throw new RuntimeException("Unable to load states definition file for "+ getClass().getName(), e);
			}
			stateTypes = (StateSection) reader.getControllerSection();
			classTypes.put(clazz, stateTypes);
		}
	}
	
	/**
	 * Indicates if the controller is waiting information from the gateway.
	 * 
	 * @return <code>true</code> if controller is waiting information and <code>false</code> otherwise
	 */
	public boolean isWaitingResult() {
		return waitingResult;
	}

	/**
	 * Returns the address of the targeted device
	 * 
	 * @return the address of the targeted device
	 */
	public Where getWhere() {
		return where;
	}

	/**
	 * Defines the address of the targeted device to control
	 * 
	 * @param newValue the address of the targeted device to control
	 */
	public void setWhere(Where newValue) {
		this.where = newValue;
		if (newValue == null) { // Manage null value because we create some
								// controller with no address (Gateway or
								// Heating central with MyHOME Bus)
			for (Property state : stateTypes.getProperties()) {
				stateList.put(new ControllerStateName(state.getName()), null);
			}
		} else {
			// Récupérer les status
			for (Property state : stateTypes.getProperties()) {
				get(new ControllerStateName(state.getName()));
			}
		}
	}

	/**
	 * Executes an action. The result is wait by a command listener.
	 * 
	 * @param what The state to update
	 * @param commandListener The listener which will wait for action result
	 */
	protected void executeAction(final State what, final CommandListener commandListener) {
		if (server == null || what == null || what.getName() == null) {
			commandListener.onCommand(new DefaultCommandResult("",
					CommandResultStatus.nok));
		} else {
			waitingResult = true;
			server.sendCommand(
					new Command(getWho(), what, where, Type.ACTION),
					new CommandListener() {
						@Override
						public void onCommand(CommandResult commandResult) {
							// TODO tester que le type du status match avec ceux supporté!
							waitingResult = false;
							commandListener.onCommand(commandResult);
						}
					}
			);
		}
	}

	/**
	 * Executes a read operation on a state name of the controller.
	 * The result is wait by a status listener.
	 * 
	 * @param stateName The state name to read
	 * @param statusListener The listener which will wait for result
	 */
	protected void executeStatus(final StateName stateName, final StatusListener statusListener) {
		if (server == null || stateName == null) {
			statusListener.onStatus(new State(stateName, null), new DefaultCommandResult("",
					CommandResultStatus.nok));
		} else {
			waitingResult = true;
			server.sendCommand(
				new Command(getWho(), new State(stateName, null), where, Type.STATUS),
				new CommandListener() {
					@Override
					public void onCommand(CommandResult result) {
						waitingResult = false;
						// TODO tester que le type du status match avec ceux supporté!
						if (CommandResultStatus.ok.equals(result.getStatus())) {
							// Return the status of the controller from the server
							State status = result.getWhat(stateName);
							statusListener.onStatus(
									status,
									result);
						} else {
							// ERROR: message not sent on Bus or error
							// return... we keep the last value
							
							statusListener.onStatus(new State(stateName, stateList.get(stateName)), result);
						}
					}
				});
		}
	}

	public abstract Who getWho();

	/**
	 * Define the gateway to connect on.
	 * 
	 * @param server
	 */
	public void setServer(Commander server) {
		this.server = server;
	}

	/**
	 * @param listener
	 *            the new change listener.
	 */
	public void addControllerChangeListener(ControllerChangeListener listener) {
		synchronized (controllerChangeListenerList) {
			controllerChangeListenerList.add(listener);
		}
	}

	/**
	 * @param listener
	 *            the change listener to remove.
	 */
	public void removeControllerChangeListener(ControllerChangeListener listener) {
		synchronized (controllerChangeListenerList) {
			controllerChangeListenerList.remove(listener);
		}
	}
	
	private void notifyStateChange(State oldStatus, State newStatus) {
		synchronized (controllerChangeListenerList) {
			for (ControllerChangeListener listener : controllerChangeListenerList) {
				listener.onStateChange(this, oldStatus, newStatus);
			}
		}
	}

	private void notifyStateChangeError(State oldStatus, State newStatus, CommandResult result) {
		synchronized (controllerChangeListenerList) {
			for (ControllerChangeListener listener : controllerChangeListenerList) {
				listener.onStateChangeError(this, oldStatus, newStatus, result);
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String name) {
		this.title = name;
	}

	/**
	 * Return the list of label associated to this component.
	 * 
	 * @return list of label.
	 */
	public LabelList getLabels() {
		return labelList;
	}

	/**
	 * Return the description of the controller
	 * 
	 * @return description of the controller
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Define the description of the controller.
	 * 
	 * @param description of the controller
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Returns the current value of a state name.
	 * 
	 * @param stateName The state name
	 * @return The current value of a state name
	 */
	public String get(String stateName) {
		if (stateName == null) {
			throw new NullPointerException("Could not set null state name.");
		}
		StateValue value = get(new ControllerStateName(stateName));
		if (value != null) {
			return value.getValue();
		}
		else {
			return null;
		}
	}

	/**
	 * Returns the current value of a state name.
	 * 
	 * @param stateName The state name
	 * @return The current value of a state name
	 */
	protected StateValue get(StateName stateName) {
		StateValue value = stateList.get(stateName);
		if (value == null) {
			executeStatus(stateName, new StatusListener() {
				@Override
				public void onStatus(State status, CommandResult result) {
					// TODO : one pb with that: if we get the value of what
					// before response we get null and nothing is done to be
					// advertise of the change when value arrive...
					stateList.put(status.getName(), status.getValue());
				}
			});	
		}
		return value;
	}
	
	/**
	 * 
	 * @param stateName
	 * @return
	 */
	public StateValueType getType(String stateName) {
		return stateTypes.getProperty(stateName).getType();
	}
	
	/**
	 * Create/update a value of the status.
	 * @param stateName The state name to update
	 * @param stateValue The new value of the state name
	 */
	public void set(String stateName, String stateValue) {
		if (stateName == null) {
			throw new NullPointerException("Could not set null state name.");
		}
		StateName key = new ControllerStateName(stateName);
		StateValueType stateType = stateTypes.getProperty(stateName).getType();
		try {
			stateType.setValue(stateValue);
		} catch (Exception e) {
			throw new IllegalArgumentException("Unable to set "+ stateName + "/" + stateValue + ": "+ e.getMessage());
		}
		set(key, stateType);
	}
	
	/**
	 * Create/update a value of the status.
	 * @param stateName The state name to update
	 * @param stateValue The new value of the state name
	 */
	protected void set(StateName stateName, StateValue stateValue) {
		// The command is sent to the gateway. Gateway transmits it to the actuator.
		// If everything is fine, Gateway provides through the monitor session
		// the new status => not need to set it here since it will be set by the
		// monitor way.
		final State oldStatus = new State(stateName, get(stateName));
		final State newStatus = new State(stateName, stateValue);
		// what = newWhat; => it will be done with changeWhat by the monitor listener
		executeAction(newStatus, new CommandListener() {
			@Override
			public void onCommand(CommandResult result) {
				if (CommandResultStatus.ok.equals(result.getStatus())) {
					// Status has been changed
					// what = newWhat; => it will be done with changeWhat by the
					// monitor listener
					// notifyWhatChange(oldStatus, newWhat); call by monitor! =>
					// it will be done with changeWhat by the monitor listener
				} else {
					// Error
					// what = oldStatus; => it will be done with changeWhat by
					// the monitor listener
					notifyStateChangeError(oldStatus, newStatus, result);
				}
			}
		});
	}

	/**
	 * Define the new {@link Status} of the device without sent the command on
	 * the bus.
	 * 
	 * @param newWhat
	 *            {@link Status} of the device.
	 */
	public void changeState(State newWhat) {
		State oldWhat = new State(newWhat.getName(), stateList.get(newWhat.getName()));
		stateList.put(newWhat.getName(), newWhat.getValue());
		notifyStateChange(oldWhat, newWhat);
	}
	
	@Override
	public String toString() {
		return toJson().toString();
	}

	@Override
	public JSONObject toJson() {
		JSONObject controllerJson = new JSONObject();
		controllerJson.put(JSON_WHO, getWho())
				 .put(JSON_TITLE, getTitle())
				 .put(JSON_DESCRIPTION, getDescription());
		if (getWhere() != null) {
			controllerJson.put(JSON_WHERE, getWhere().getTo());
		}
		JSONObject states = new JSONObject();
		if (! stateList.isEmpty()) {
			for (Entry<StateName, StateValue> entry : stateList.entrySet()) {
				StateValue sv = entry.getValue();
				states.put(entry.getKey().getName(), sv == null ? null : sv.getValue());
			}
		}
		controllerJson.put(JSON_STATES, states);
		return controllerJson;
	}

	@Override
	public void fromJson(JSONObject jsonObject) {
		setTitle(jsonObject.getString(JSON_TITLE));
		try {
			setDescription(jsonObject.getString(JSON_DESCRIPTION));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String to = jsonObject.getString(JSON_WHERE);
		if (!"null".equals(String.valueOf(to))) {
			setWhere(new Where(to, to));
		}

//		JSONObject states = jsonObject.getJSONObject(JSON_STATES);
//		for (final String name: states.keySet()) {
//			String value = states.getString(name);
//			StateName sname = new StateName() {
//				
//				@Override
//				public String getName() {
//					return name;
//				}
//			};
//			StateValue svalue; Les valeurs sont en lecture seul???
//			try {
//				svalue = stateTypes.get(sname).newInstance();
//				svalue.setValue(value);
//				stateList.put(sname, svalue);
//			} catch (InstantiationException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
	}
	
	/**
	 * 
	 * @author DRIESBACH Olivier
	 * @version 1.0
	 * @since 1.0
	 */
	private class ControllerStateName implements StateName {
		
		private String stateName;
		
		private ControllerStateName(String stateName) {
			this.stateName = stateName.toLowerCase();			
		}

		@Override
		public String getName() {
			return stateName;
		}
	}
}
