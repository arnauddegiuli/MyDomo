//package com.homesnap.engine.test;
//
///*
// * #%L
// * MyDomoEngine
// * %%
// * Copyright (C) 2011 - 2013 A. de Giuli
// * %%
// * This file is part of MyDomo done by A. de Giuli (arnaud.degiuli(at)free.fr).
// * 
// *     MyDomo is free software: you can redistribute it and/or modify
// *     it under the terms of the GNU General Public License as published by
// *     the Free Software Foundation, either version 3 of the License, or
// *     (at your option) any later version.
// * 
// *     MyDomo is distributed in the hope that it will be useful,
// *     but WITHOUT ANY WARRANTY; without even the implied warranty of
// *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// *     GNU General Public License for more details.
// * 
// *     You should have received a copy of the GNU General Public License
// *     along with MyDomo.  If not, see <http://www.gnu.org/licenses/>.
// * #L%
// */
//
//
//import org.junit.Assert;
//import org.junit.Test;
//
//import com.homesnap.engine.connector.CommandResult;
//import com.homesnap.engine.controller.Controller;
//import com.homesnap.engine.controller.ControllerChangeListener;
//import com.homesnap.engine.controller.automation.Automation;
//import com.homesnap.engine.controller.automation.stateValue.AutomationStatusValue;
//import com.homesnap.engine.controller.what.State;
//import com.homesnap.engine.services.ControllerService;
//import com.homesnap.engine.services.impl.OpenWebNetControllerService;
//
//public class AutomationTest {
//
//	private ControllerService s = new OpenWebNetControllerService("localhost", 1234, 12345);
//	private Object lock = new Object();
//	
//	@Test
//	public void statusUpDownTest() {
//		
//		final Automation automation = s.createController(Automation.class, "32");
//		
//		// Listener will make us availabe to wait response from server
//		automation.addControllerChangeListener(new ControllerChangeListener() {
//
//			@Override
//			public void onStateChangeError(Controller controller,
//					State oldStatus, State newStatus, CommandResult result) {
//				synchronized (lock) {
//					// When response from server is here we unlock the thread
//					System.out.println("Unlock...");
//					lock.notify();
//				}
//			}
//			
//			@Override
//			public void onStateChange(Controller controller,
//					State oldStatus, State newStatus) {
//				synchronized (lock) {
//					// When response from server is here we unlock the thread
//					System.out.println("Unlock...");
//					lock.notify();
//				}
//			}
//		});
//		
//		
//		// First we just wait 1 second to be sure the controller is initialize 
//		try {
//			synchronized (lock) {
//				
//				lock.wait(1000);
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// By default server send back a OFF status. If value == null, it is a bug or just server have not enough time (1 second) to respond
//		Assert.assertNotNull(automation.getStatus());
//		Assert.assertEquals(AutomationStatusValue.STOP, automation.getStatus());
//		
//		// Now set the value to AUTOMATION_DOWN
//		automation.setStatus(AutomationStatusValue.DOWN);
//		System.out.println("Wait...");
//		
//		// Wait the response from the server
//		try {
//			synchronized (lock) {
//				
//				lock.wait();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		// Check that after the server response now the status is ON
//		Assert.assertNotNull(automation.getStatus());
//		Assert.assertEquals(AutomationStatusValue.DOWN , automation.getStatus());
//		
//		// Switch UP now again
//		automation.setStatus(AutomationStatusValue.UP);
//		System.out.println("Wait...");
//		
//		try {
//			synchronized (lock) {
//				
//				lock.wait();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		Assert.assertNotNull(automation.getStatus());
//		Assert.assertEquals(AutomationStatusValue.UP , automation.getStatus());
//
//		// Switch OFF now again
//		automation.setStatus(AutomationStatusValue.STOP);
//		System.out.println("Wait...");
//		
//		try {
//			synchronized (lock) {
//				
//				lock.wait();
//			}
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}
//		
//		Assert.assertNotNull(automation.getStatus());
//		Assert.assertEquals(AutomationStatusValue.STOP , automation.getStatus());
//
//		
//		System.out.println("Finish...");
//
//		
//	}
//}
