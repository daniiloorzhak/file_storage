package ru.oorzhak.filestorage.service;

import ru.oorzhak.filestorage.dto.AccountDTO;
import ru.oorzhak.filestorage.models.Account;

import java.util.List;

public interface AccountService {
    Account findById(Long id);
    Account findByUsername(String username);
    List<Account> findAll();
    Account save(AccountDTO accountDTO);
}
