package com.api.bugtracker.dtos;

import com.api.bugtracker.models.Ticket;

import java.time.LocalDateTime;

public record TicketResponseDTO(Long id, String title, LocalDateTime created_at, String status) {
    public TicketResponseDTO(Ticket ticket){
        this(ticket.getId(), ticket.getTitle(), ticket.getCreated_at(), ticket.getStatus());
    }
}
