package com.api.bugtracker.controllers;

import com.api.bugtracker.dtos.MessageRequestDTO;
import com.api.bugtracker.dtos.MessageResponseDTO;
import com.api.bugtracker.dtos.TicketResponseDTO;
import com.api.bugtracker.models.Message;
import com.api.bugtracker.models.Ticket;
import com.api.bugtracker.models.User;
import com.api.bugtracker.repositories.MessageRepository;
import com.api.bugtracker.services.AuthService;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("message")
public class MessageController {

    @Autowired
    private MessageRepository repository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    private void saveMessage(@RequestBody MessageRequestDTO data){
        Message messageData = new Message(data);
        messageData.setCreated_at(LocalDateTime.now().withNano(0));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        messageData.setUser_id(user.getId());
//        messageData.setTicketId(1);
        repository.save(messageData);
        return;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<MessageResponseDTO> getAll(){
        List<MessageResponseDTO> messageList = repository.findAll().stream().map(MessageResponseDTO::new).toList();
        return messageList;

    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{ticketId}")
    public List<MessageResponseDTO> getAllByTicketId(@PathVariable Integer ticketId) {
        List<MessageResponseDTO> messageList;

        messageList = repository.findByTicketId(ticketId).stream().map(MessageResponseDTO::new).toList();

        return messageList;
    }



}
