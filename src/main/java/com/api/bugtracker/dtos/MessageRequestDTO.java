package com.api.bugtracker.dtos;

import com.api.bugtracker.models.Message;

public record MessageRequestDTO(String text, Integer ticketId) {

}
