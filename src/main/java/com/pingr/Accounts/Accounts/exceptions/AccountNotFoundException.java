package com.pingr.Accounts.Accounts.exceptions;

public class AccountNotFoundException extends RuntimeException {
    public AccountNotFoundException(Long id) {
        super("Account not found: [id: " + id + "]");
    }

    public AccountNotFoundException(Long id, Throwable cause) {
        super("Account not found: [id: " + id + "]", cause);
    }
}
