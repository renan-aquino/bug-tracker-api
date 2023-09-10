package com.api.bugtracker.repositories;

import com.api.bugtracker.models.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MessageRepository extends JpaRepository<Message, Long> {
    List<Message> findByTicketId(@Param("ticketId") Integer ticketId);

//    Optional<Object> findByTicketId(Long ticketId);
}
