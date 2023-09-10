package com.api.bugtracker.repositories;

import com.api.bugtracker.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
