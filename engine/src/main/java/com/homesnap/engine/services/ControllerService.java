package com.homesnap.engine.services;

import com.homesnap.engine.connector.ConnectionListener;
import com.homesnap.engine.connector.UnknownControllerListener;
import com.homesnap.engine.controller.Controller;

/*
 * #%L
 * HomeSnapEngine
 * %%
 * Copyright (C) 2011 - 2016 A. de Giuli
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


/**
 * ControllerService implements feature about controller. 
 *
 */
public interface ControllerService {

	/**
	 * Create a controller. Controller created is already connected
	 * to open web server (with a monitor session and a command session):
	 * this means that when you get the status ({@link Controller#getStatus(String)})
	 * or change it, the order is sent to the open web server. 
	 * @param clazz the class of the controller you would like create.
	 * @param where the address of the device
	 * @return the new created and initialized controller
	 */
	public abstract <T extends Controller> T createController(
			Class<T> clazz, String where);
	
	public abstract boolean isCommanderConnected();
	
	public abstract boolean isMonitorConnected();
	
	/**
	 * Define the ip of the gateway to connect.
	 * @param ip ipv4
	 */
	public abstract void setIp(String ip);
	
	/**
	 * Define the password of the gateway to connect.
	 * @param password the password
	 */
	public abstract void setPassword(Integer password);
	
	/**
	 * Define the port of the gateway to connect.
	 * @param port
	 */
	public abstract void setPort(int port);

	public void onDestroy();
	
	/**
	 * Add a listener on new monitor session connection
	 * @param listener
	 */
	public void addMonitorConnectionListener(ConnectionListener listener);

	/**
	 * Remove a listener on new monitor session connection
	 * @param listener
	 */
	public void removeMonitorConnectionListener(ConnectionListener listener);

	
	/**
	 * Add a listener on new command session connection
	 * @param listener
	 */
	public void addCommanderConnectionListener(ConnectionListener listener);

	/**
	 * Remove a listener on new command session connection
	 * @param listener
	 */
	public void removeCommanderConnectionListener(ConnectionListener listener);

	
	/**
	 * Add a listener on new controller detected. A new controller is detected
	 * when monitor session get a message and doesn't know the where.
	 * @param listener
	 */
	public void addUnknowControllerListener(UnknownControllerListener listener);

	/**
	 * Remove a listener on new controller detected. A new controller is detected
	 * when monitor session get a message and doesn't know the where.
	 * @param listener
	 */
	public void removeUnknowControllerListener(UnknownControllerListener listener);
	
	/**
	 * Force connection for Commander and Monitor if there is no connection.
	 */
	public void connect();
	
	/**
	 * Add a listener on scan.
	 * @param listener
	 */
	public void addScanListener(ScanListener listener);

	/**
	 * Remove a listener on scan.
	 * @param listener
	 */
	public void removeScanListener(ScanListener listener);

	
	/**
	 * Lunch a scan.
	 */
	public void scan();
}
