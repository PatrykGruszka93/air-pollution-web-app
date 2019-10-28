package com.gruszka.airpollutionwebapp.gios.model;

public class PollutionData {

    private String key;
    private PollutionDataValue[] values;

    public PollutionData() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PollutionDataValue[] getValues() {
        return values;
    }

    public void setValues(PollutionDataValue[] values) {
        this.values = values;
    }
}
