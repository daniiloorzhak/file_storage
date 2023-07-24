package ru.oorzhak.filestorage.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Данные пользователя
 */

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDTO implements Serializable {
    @NotNull
    @Size(min = 6, max = 30)
    private String username;
    @NotBlank
    private String password;
    @NotBlank
    private String confirmPassword;
}
