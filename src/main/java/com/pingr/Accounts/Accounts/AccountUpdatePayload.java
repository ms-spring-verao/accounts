package com.pingr.Accounts.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AccountUpdatePayload {
    @JsonProperty
    private final String attributeName;

    @JsonProperty
    private final String newAttributeValue;

    public AccountUpdatePayload(String attributeName, String newAttributeValue) {
        this.attributeName = attributeName;
        this.newAttributeValue = newAttributeValue;
    }

    public String getAttributeName() {
        return attributeName;
    }

    public String getNewAttributeValue() {
        return newAttributeValue;
    }
}
