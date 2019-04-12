package com.ttn.linkSharing.enums;

public enum Seriousness {
    SERIOUS("Serious"), CASUAL("Casual"), VERY_SERIOUS("Very Serious");

    private String value;

    Seriousness(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
