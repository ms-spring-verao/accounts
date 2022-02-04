package com.pingr.Accounts.Accounts.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingr.Accounts.Accounts.Account;

import java.util.HashMap;
import java.util.Map;

@JsonSerialize
public class AccountUpdatedEvent {
    @JsonProperty
    private final String eventType;

    @JsonProperty
    private final Long accountId;

    @JsonProperty
    private final Map<String, Object> payload;

    private AccountUpdatedEvent(String eventType, Long accountId, Map<String, Object> payload) {
        this.eventType = eventType;
        this.accountId = accountId;
        this.payload = payload;
    }

    public static AccountUpdatedEvent of(Account account, String updatedAttribute) {
        Map<String, Object> payload = new HashMap<>();

        switch (updatedAttribute) {
            case "email":
                payload.put("email", account.getEmail());
                break;
            case "username":
                payload.put("username", account.getUsername());
                break;
        }

        return new AccountUpdatedEvent(
                "AccountUpdatedEvent",
                account.getId(),
                payload
        );
    }
}
