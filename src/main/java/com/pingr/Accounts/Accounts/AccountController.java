package com.pingr.Accounts.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {
    private final AccountService service;

    @Autowired
    public AccountController(AccountService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account createOneAccount(@RequestBody Account account) {
        return this.service.createAccount(account);
    }

    @PutMapping(path = "/{id}")
    public Account updateOneAccount(@PathVariable("id") Long id, @RequestBody AccountUpdatePayload payload) {
        return this.service.updateAccount(id, payload);
    }

    @DeleteMapping(path = "/{id}")
    public Account deleteOneAccount(@PathVariable("id") Long id) {
        return this.service.deleteAccount(id);
    }
}
