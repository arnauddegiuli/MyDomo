package com.homesnap.engine.controller.gateway.statename;

/*
 * #%L
 * HomeSnapEngine
 * %%
 * Copyright (C) 2011 - 2014 A. de Giuli
 * %%
 * This file is part of MyDomo done by A. de Giuli (arnaud.degiuli(at)free.fr).
 * 
 *     MyDomo is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 * 
 *     MyDomo is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 * 
 *     You should have received a copy of the GNU General Public License
 *     along with MyDomo.  If not, see <http://www.gnu.org/licenses/>.
 * #L%
 */

import com.homesnap.engine.controller.what.StateName;

public enum GatewayStateName implements StateName {
	DATE,
	DATETIME,
	DISTRIBUTION_VERSION,
	FIRMWARE_VERSION,
	IP_ADDRESS,
	KERNEL_VERSION,
	MAC_ADDRESS,
	MODEL,
	NETMASK,
	TIME,
	UPTIME;
	
	private GatewayStateName() {
	}
	
	@Override
	public String getName() {
		return name();
	}
	
	public static GatewayStateName fromValue(String code) {
		for (GatewayStateName gd : GatewayStateName.values()) {
			if (gd.getName().equals(code))
				return gd;
		}
		return null;
	}
}