package ru.oorzhak.filestorage.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * Данные пользователя
 */

@Data
public class AccountDTO implements Serializable {
    @NotEmpty
    @Size(min = 6, max = 30)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
