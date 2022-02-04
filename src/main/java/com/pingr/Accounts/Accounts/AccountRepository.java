package com.pingr.Accounts.Accounts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "SELECT id, username FROM Account WHERE username like %?1%", nativeQuery = true)
    List<AccountSearchView> searchByUsernameAlike(String term);
}
