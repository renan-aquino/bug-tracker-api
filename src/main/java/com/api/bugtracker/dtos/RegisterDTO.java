package com.api.bugtracker.dtos;

import com.api.bugtracker.models.UserRole;

public record RegisterDTO (String login, String password, UserRole role){
}
