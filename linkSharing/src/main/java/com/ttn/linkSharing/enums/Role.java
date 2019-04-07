package com.ttn.linkSharing.enums;

public enum Role {
    USER("User"), ADMIN("Admin");
    String value;

    Role(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}