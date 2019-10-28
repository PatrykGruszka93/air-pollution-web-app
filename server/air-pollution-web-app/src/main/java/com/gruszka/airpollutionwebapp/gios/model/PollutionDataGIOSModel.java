package com.gruszka.airpollutionwebapp.gios.model;

public class PollutionDataGIOSModel {

    private String key;
    private PollutionDataValueGIOSModel[] values;

    public PollutionDataGIOSModel() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public PollutionDataValueGIOSModel[] getValues() {
        return values;
    }

    public void setValues(PollutionDataValueGIOSModel[] values) {
        this.values = values;
    }
}
