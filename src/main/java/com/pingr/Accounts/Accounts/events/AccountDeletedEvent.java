package com.pingr.Accounts.Accounts.events;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.pingr.Accounts.Accounts.Account;

@JsonSerialize
public class AccountDeletedEvent {
    @JsonProperty
    private String eventType;

    @JsonProperty
    private Long accountId;

    private AccountDeletedEvent(String eventType, Long accountId) {
        this.eventType = eventType;
        this.accountId = accountId;
    }

    public static AccountDeletedEvent of(Account account) {
        return new AccountDeletedEvent(
                "AccountDeletedEvent",
                account.getId()
        );
    }
}
