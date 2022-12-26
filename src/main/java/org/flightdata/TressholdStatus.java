package org.flightdata;

public enum TressholdStatus {
    GREAT("Sales of tickets were great"), AVERAGE("Sales of tickets were average"), POOR("Sales of tickets were poor");

    private String value;

    TressholdStatus(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
