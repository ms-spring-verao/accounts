package com.pingr.Accounts.Accounts;

import com.pingr.Accounts.Accounts.exceptions.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Account updateAccount(Long id, AccountUpdatePayload updatePayload) throws AccountNotFoundException, IllegalArgumentException {
        Optional<Account> accOptional = this.repo.findById(id);

        if (!accOptional.isPresent()) {
            throw new AccountNotFoundException(id);
        }

        String attrName = updatePayload.getAttributeName();

        if (!List.of("email", "username", "password").contains(attrName)) {
            throw new IllegalArgumentException("Account updating violates restrictions [attribute: " + attrName + " doesn't exist on type Account]");
        }

        String newValue = updatePayload.getNewAttributeValue();
        Account account = accOptional.get();
        switch (attrName) {
            case "email":
                account.setEmail(newValue);
                break;
            case "password":
                account.setPassword(newValue);
                break;
            case "username":
                account.setUsername(newValue);
                break;
        }

        try {
            Account acc = this.repo.save(account);
            this.producer.emitAccountUpdatedEvent(acc, attrName);
            return acc;
        } catch(Exception e) {
            throw new IllegalArgumentException("Account updating violates restrictions" + "[account: " + account + "]");
        }
    }

    public Account deleteAccount(Long id) {
        Optional<Account> accOptional = this.repo.findById(id);

        if (!accOptional.isPresent()) {
            throw new AccountNotFoundException(id);
        }

        this.repo.deleteById(id);
        Account account = accOptional.get();
        this.producer.emitAccountDeletedEvent(account);
        return account;
    }

    public Account readAccount(Long id) {
        Optional<Account> accOptional = this.repo.findById(id);

        if (!accOptional.isPresent()) {
            throw new AccountNotFoundException(id);
        }

        return accOptional.get();
    }

    public List<AccountSearchView> search(String term) {
        return this.repo.searchByUsernameAlike(term);
    }
}
