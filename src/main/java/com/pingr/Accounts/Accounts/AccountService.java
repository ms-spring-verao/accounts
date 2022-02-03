package com.pingr.Accounts.Accounts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
    private final AccountRepository repo;
    private final ProducerService producer;

    @Autowired
    public AccountService(AccountRepository repo, ProducerService producer) {
        this.repo = repo;
        this.producer = producer;
    }

    //   tem ID, está salva no banco
    //     |
    //     |         não tem ID, não está no banco ainda
    //     |                                  |
    //     v                                  v
    public Account createAccount(Account account) throws IllegalArgumentException {
        try {
            Account acc = this.repo.save(account);
            this.producer.emitAccountCreatedEvent(acc);
            return acc;
        } catch(Exception e) {
            throw new IllegalArgumentException("Account creation violates restrictions" + "[account: " + account + "]");
        }
    }
}
