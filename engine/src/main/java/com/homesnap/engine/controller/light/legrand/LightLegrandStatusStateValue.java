package com.homesnap.engine.controller.light.legrand;

import com.homesnap.engine.controller.what.StateValue;

/**
 * Enumeration of the possible values of the state name {@link LightLegrandStateName#STATUS}.
 * 
 * Generated by ControllerStateGenerator
 */
public enum LightLegrandStatusStateValue implements StateValue {
	
	LIGHT_OFF,
	LIGHT_ON,
	LIGHT_ON_20_PERCENT,
	LIGHT_ON_30_PERCENT,
	LIGHT_ON_40_PERCENT,
	LIGHT_ON_50_PERCENT,
	LIGHT_ON_60_PERCENT,
	LIGHT_ON_70_PERCENT,
	LIGHT_ON_80_PERCENT,
	LIGHT_ON_90_PERCENT,
	LIGHT_ON_100_PERCENT,
	LIGHT_ON_DURING_1_MIN,
	LIGHT_ON_DURING_2_MIN,
	LIGHT_ON_DURING_3_MIN,
	LIGHT_ON_DURING_4_MIN,
	LIGHT_ON_DURING_5_MIN,
	LIGHT_ON_DURING_15_MIN,
	LIGHT_ON_DURING_30_SEC,
	LIGHT_ON_DURING_HALF_SEC,
	LIGHT_ERROR,
	LIGHT_ON_BLINKING_HALF_SEC,
	LIGHT_ON_BLINKING_1_SEC,
	LIGHT_ON_BLINKING_1_AND_HALF_SEC,
	LIGHT_ON_BLINKING_2_SEC,
	LIGHT_ON_BLINKING_2_AND_HALF_SEC,
	LIGHT_ON_BLINKING_3_SEC,
	LIGHT_ON_BLINKING_3_AND_HALF_SEC,
	LIGHT_ON_BLINKING_4_SEC,
	LIGHT_ON_BLINKING_4_AND_HALF_SEC,
	LIGHT_ON_BLINKING_5_SEC,
	LIGHT_ON_UP_ONE_LEVEL,
	LIGHT_OFF_ONE_LEVEL,
	LIGHT_FORCE_ON,
	LIGHT_FORCE_OFF;
	
	@Override
	public String getValue() {
		return name().toLowerCase();
	}
}