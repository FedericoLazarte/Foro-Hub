package com.alura.forohub.domain.user;

import jakarta.validation.constraints.NotBlank;

public record UserAuthenticationDTO(
        @NotBlank
        String login,
        @NotBlank
        String password
) {
}
