package io.klogs.model.common;

public enum CommApplyType {
    IN(1),
    OUT(2);

    private final int value;

    CommApplyType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
