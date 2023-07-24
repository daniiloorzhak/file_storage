package ru.oorzhak.filestorage.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.oorzhak.filestorage.dto.AccountDTO;
import ru.oorzhak.filestorage.models.Account;
import ru.oorzhak.filestorage.service.AccountService;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Override
    @Transactional(readOnly = true)
    public Account findById(Long id) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Account findByUsername(String username) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Account> findAll() {
        return null;
    }

    @Override
    @Transactional
    public Account save(AccountDTO accountDTO) {
        return null;
    }
}
