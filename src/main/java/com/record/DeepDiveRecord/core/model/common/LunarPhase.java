package com.record.DeepDiveRecord.core.model.common;

public enum LunarPhase {
    WANING_MOON(1),
    NEW_MOON(2),
    CRESCENT_MOON(3),
    FULL_MOON(4);
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
