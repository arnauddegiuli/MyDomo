//package com.homesnap.engine.connector.openwebnet.gateway.dimension;
//
///*
// * #%L
// * HomeSnapEngine
// * %%
// * Copyright (C) 2011 - 2015 A. de Giuli
// * %%
// * This file is part of HomeSnap done by Arnaud de Giuli (arnaud.degiuli(at)free.fr)
// *     helped by Olivier Driesbach (olivier.driesbach(at)gmail.com).
// * 
// *     HomeSnap is free software: you can redistribute it and/or modify
// *     it under the terms of the GNU General Public License as published by
// *     the Free Software Foundation, either version 3 of the License, or
// *     (at your option) any later version.
// * 
// *     HomeSnap is distributed in the hope that it will be useful,
// *     but WITHOUT ANY WARRANTY; without even the implied warranty of
// *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// *     GNU General Public License for more details.
// * 
// *     You should have received a copy of the GNU General Public License
// *     along with HomeSnap. If not, see <http://www.gnu.org/licenses/>.
// * #L%
// */
//
//import java.util.Calendar;
//import java.util.GregorianCalendar;
//
//import com.homesnap.engine.connector.openwebnet.dimension.DimensionStatusImpl;
//import com.homesnap.engine.connector.openwebnet.dimension.DimensionValue;
//import com.homesnap.engine.connector.openwebnet.dimension.DimensionValueImpl;
//import com.homesnap.engine.connector.openwebnet.gateway.GatewayDimensionConverter;
//import com.homesnap.engine.controller.what.StateValue;
//import com.homesnap.engine.controller.what.impl.DateState;
//
//public class DateTime extends DimensionStatusImpl<DateState> {
//	
//	private int HOURS_POS = 0;
//	private int MINUTES_POS = 1;
//	private int SECONDS_POS = 2;
//	private int TIMEZONE_POS = 3;
//	private int DAYOFWEEK_POS = 4;
//	private int DAY_POS = 5;
//	private int MONTH_POS = 6;
//	private int YEAR_POS = 7;
//	
//	public DateTime() {
//		super(new DimensionValue[] { 
//				
//				new DimensionValueImpl(), // Hours
//				new DimensionValueImpl(), // Minute
//				new DimensionValueImpl(), // Second
//				new DimensionValueImpl(), // Timezone
//				new DimensionValueImpl(), // Day of week
//				new DimensionValueImpl(), // Day
//				new DimensionValueImpl(), // Month
//				new DimensionValueImpl()  // Year
//				},
//			GatewayDimensionConverter.DATETIME.getCode()
//		);
//	}
//
//	private java.util.Date getDate() {
//		Calendar c = new GregorianCalendar();
//		c.set(Calendar.DAY_OF_WEEK, getIntValue(DAYOFWEEK_POS));
//		c.set(Calendar.DAY_OF_MONTH, getIntValue(DAY_POS));
//		c.set(Calendar.MONTH, getIntValue(MONTH_POS));
//		c.set(Calendar.YEAR, getIntValue(YEAR_POS));
//		c.set(Calendar.HOUR, getIntValue(HOURS_POS));
//		c.set(Calendar.MINUTE, getIntValue(MINUTES_POS));
//		c.set(Calendar.SECOND, getIntValue(SECONDS_POS));
//		c.setTimeZone(getTimeZoneValue(TIMEZONE_POS));
//
//		return c.getTime();
//	}
//
//	public void setDate(java.util.Date date) {
//		Calendar c = new GregorianCalendar();
//		c.setTime(date);
//		setIntValue(c.get(Calendar.DAY_OF_WEEK), DAYOFWEEK_POS, 2);
//		setIntValue(c.get(Calendar.DAY_OF_MONTH), DAY_POS, 2);
//		setIntValue(c.get(Calendar.MONTH), MONTH_POS, 2);
//		setIntValue(c.get(Calendar.YEAR), YEAR_POS, 4);
//		setIntValue(c.get(Calendar.HOUR), HOURS_POS, 2);
//		setIntValue(c.get(Calendar.MINUTE), MINUTES_POS, 2);
//		setIntValue(c.get(Calendar.SECOND), SECONDS_POS, 2);
//		setTimeZoneValue(c.getTimeZone(), TIMEZONE_POS);
//	}
//
//	@Override
//	public DateState getStateValue() {
//		return new DateState(getDate());
//	}
//
//	@Override
//	public void setStateValue(StateValue value) {
//		setDate(((DateState)value).getDate());
//	}
//}
