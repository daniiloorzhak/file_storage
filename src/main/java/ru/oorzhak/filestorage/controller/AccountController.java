package ru.oorzhak.filestorage.controller;

import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ru.oorzhak.filestorage.dto.AccountDTO;
import ru.oorzhak.filestorage.models.Account;
import ru.oorzhak.filestorage.service.AccountService;
import ru.oorzhak.filestorage.util.JwtUtil;

import java.util.Map;

/**
 * API для работы с пользователями
 */
@RestController
@RequestMapping(value = "accounts", produces = MediaType.APPLICATION_JSON_VALUE)
public class AccountController {
    private final AccountService accountService;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AccountController(AccountService accountService, ModelMapper modelMapper, AuthenticationManager authenticationManager) {
        this.accountService = accountService;
        this.modelMapper = modelMapper;
        this.authenticationManager = authenticationManager;
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> register(@Valid @RequestBody AccountDTO accountDTO) {

        Account account = accountService.save(accountDTO);

        String username = account.getUsername();

        var location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{username}")
                .buildAndExpand(account.getUsername())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String, String>> login(@Valid @RequestBody AccountDTO accountDTO) {
        var authToken = new UsernamePasswordAuthenticationToken(accountDTO.getUsername(), accountDTO.getPassword());
        var authentication = authenticationManager.authenticate(authToken);

        String jwtToken = JwtUtil.generateToken(authentication.getName());

        return ResponseEntity.ok(Map.of("jwt-token", jwtToken));
    }
    
    @GetMapping("{username}")
    public ResponseEntity<AccountDTO> getByUsername(@PathVariable String username) {
        Account account = accountService.findByUsername(username);
        AccountDTO accountDTO = new AccountDTO();
        modelMapper.map(account, accountDTO);
        return ResponseEntity.ok(accountDTO);
    }

    @GetMapping("{id}")
    public ResponseEntity<AccountDTO> getById(@PathVariable Long id) {
        Account account = accountService.findById(id);
        AccountDTO accountDTO = new AccountDTO();
        modelMapper.map(account, accountDTO);
        return ResponseEntity.ok(accountDTO);
    }
}