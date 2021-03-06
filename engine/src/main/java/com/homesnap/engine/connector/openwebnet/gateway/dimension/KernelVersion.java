//package com.homesnap.engine.connector.openwebnet.gateway.dimension;
//
//import com.homesnap.engine.connector.openwebnet.dimension.DimensionStatusImpl;
//import com.homesnap.engine.connector.openwebnet.dimension.DimensionValue;
//import com.homesnap.engine.connector.openwebnet.dimension.DimensionValueImpl;
//import com.homesnap.engine.connector.openwebnet.gateway.GatewayDimensionConverter;
//import com.homesnap.engine.controller.what.StateValue;
//import com.homesnap.engine.controller.what.impl.VersionState;
//
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
//
//
//public class KernelVersion extends DimensionStatusImpl<VersionState> {
//	
//	private int VERSION_POS = 0;
//	private int RELEASE_POS = 1;
//	private int BUILD_POS = 2;
//	
//	public KernelVersion() {
//		super(new DimensionValue[] { 
//				new DimensionValueImpl(), // Version
//				new DimensionValueImpl(), // Release
//				new DimensionValueImpl()  // Build
//				},
//			GatewayDimensionConverter.KERNEL_VERSION.getCode()
//		);
//	}
//
//	@Override
//	public VersionState getStateValue() {
//		int version = getIntValue(VERSION_POS);
//		int release = getIntValue(RELEASE_POS);
//		int build = getIntValue(BUILD_POS);
//
//		VersionState ver = new VersionState(version, release, build);
//
//		return ver;
//	}
//
//	@Override
//	public void setStateValue(StateValue value) {
//		// TODO throw new ReadOnlyException(); // read only dimension
//	}
//
//	public void setDistributionVersion(int version, int release, int build) {
//		setIntValue(version, VERSION_POS, 2);
//		setIntValue(release, RELEASE_POS, 2);
//		setIntValue(build, BUILD_POS, 2);
//	}
//}
