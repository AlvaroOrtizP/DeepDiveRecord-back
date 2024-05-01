package com.record.DeepDiveRecord.core.model.common;

public enum LunarPhase {
    NEW_MOON(1),
    WAXING_CRESCENT(2),
    FIRST_QUARTER(3),
    WAXING_GIBBOUS(4),
    FULL_MOON(5),
    WANING_GIBBOUS(6),
    LAST_QUARTER(7),
    WANING_CRESCENT(8);
    private Integer value;

    private LunarPhase(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public static LunarPhase fromValue(Integer value) {
        for (LunarPhase b : LunarPhase.values()) {
            if (b.value.equals(value)) {
                return b;
            }
        }
        throw new IllegalArgumentException("Unexpected value " + value);
    }
}
