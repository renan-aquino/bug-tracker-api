package com.api.bugtracker.dtos;

import com.api.bugtracker.models.Message;

import java.time.LocalDateTime;

public record MessageResponseDTO(Long id, String text, LocalDateTime created_at, Integer user_id, Integer ticket_id) {
    public MessageResponseDTO(Message message) {
        this(message.getId(), message.getText(), message.getCreated_at(), message.getUser_id(), message.getTicketId());
    }
}