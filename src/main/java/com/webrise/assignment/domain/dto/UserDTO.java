package com.webrise.assignment.domain.dto;

public record UserDTO(
        String userId, String email, String encryptedPassword
        ) {
}
