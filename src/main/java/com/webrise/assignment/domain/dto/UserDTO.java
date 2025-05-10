package com.webrise.assignment.domain.dto;

public record UserDTO(String id,
                      String email,
                      String encryptedPassword) {
}
