package com.pingr.Accounts.Accounts;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class AccountEventView {
    @JsonProperty
    private Long id;
    @JsonProperty
    private String username;
    @JsonProperty
    private String email;

    public AccountEventView(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public static AccountEventView createFromAccount(Account account) {
        return new AccountEventView(
                account.getId(),
                account.getUsername(),
                account.getEmail()
        );
    }
}
