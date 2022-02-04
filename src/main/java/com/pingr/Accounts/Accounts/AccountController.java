package com.pingr.Accounts.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping(path = "/{id}")
    public Account readOneAccount(@PathVariable("id") Long id) {
        return this.service.readAccount(id);
    }

    @GetMapping
    public List<AccountSearchView> fuzzySearch(@RequestParam("term") String term) {
        return this.service.search(term);
    }
}
