package com.homesnap.engine.controller.what.impl;


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

import com.homesnap.engine.controller.what.StateValue;

public class MacAddressValue implements StateValue {

	private byte[] macAdress = new byte[] {0, 0, 0, 0, 0, 0};
	
	public MacAddressValue() {
	}

	@Override
	public String getValue() {
		return "".concat(String.valueOf(macAdress[0]))
				.concat(".").concat(String.valueOf(macAdress[1]))
				.concat(".").concat(String.valueOf(macAdress[2]))
				.concat(".").concat(String.valueOf(macAdress[3]))
				.concat(".").concat(String.valueOf(macAdress[4]))
				.concat(".").concat(String.valueOf(macAdress[5]));
	}

	public byte[] getMacAddress() {
		return macAdress;
	}

	public void setMacAddress(byte[] address) throws IncorrectMacAddressException {
		if (address == null || address.length != 6) {
			throw new IncorrectMacAddressException();
		} else {
			macAdress = address;
		}
	}
}
