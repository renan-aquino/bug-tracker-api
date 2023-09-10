package com.api.bugtracker.dtos;


public record LoginResponseDTO(String token, UserDTO user) {
}
