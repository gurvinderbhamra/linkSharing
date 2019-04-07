package com.ttn.linkSharing.enums;

public enum Visibility {

    PUBLIC("public"), PRIVATE("private");

    private String value;

    Visibility(String value){
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
