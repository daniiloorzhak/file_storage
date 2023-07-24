package ru.oorzhak.filestorage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.oorzhak.filestorage.models.Account;

public interface UserRepository extends JpaRepository<Account, Long> {
    Account findByUsername(String username);
    Account findById(long id);
}
