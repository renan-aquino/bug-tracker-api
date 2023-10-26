package com.api.bugtracker.controllers;

import com.api.bugtracker.dtos.*;
import com.api.bugtracker.models.Message;
import com.api.bugtracker.models.User;
import com.api.bugtracker.repositories.MessageRepository;
import com.api.bugtracker.repositories.TicketRepository;
import com.api.bugtracker.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("ticket")
public class TicketController {

    @Autowired
    private TicketRepository repository;

    @Autowired
    private MessageRepository messageRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    private ResponseEntity saveTicket(@RequestBody TicketRequestDTO data){
        Ticket ticketData = new Ticket(data);
        ticketData.setCreated_at(LocalDateTime.now().withNano(0));
        repository.save(ticketData);

        MessageRequestDTO messageDTO = new MessageRequestDTO(data.text(), Math.toIntExact(ticketData.getId()));

        Message message = new Message(messageDTO);
        message.setTicketId(Math.toIntExact(ticketData.getId()));
        message.setCreated_at(LocalDateTime.now().withNano(0));

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) auth.getPrincipal();

        message.setUser_id(user.getId());

        messageRepository.save(message);
        return ResponseEntity.ok(new TicketResponseDTO(ticketData));
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<TicketResponseDTO> getAll(){
        List<TicketResponseDTO> ticketList = repository.findAll().stream().map(TicketResponseDTO::new).toList();
        return ticketList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<TicketResponseDTO> getTicketById(@PathVariable Long id) {
        // Find the ticket by ID in the repository
        Optional<Ticket> optionalTicket = repository.findById(id);

        if (optionalTicket.isPresent()) {
            // Convert the Ticket to a TicketResponseDTO and return it
            TicketResponseDTO ticketResponseDTO = new TicketResponseDTO(optionalTicket.get());
            return ResponseEntity.ok(ticketResponseDTO);
        } else {
            // If no ticket with the given ID is found, return a 404 Not Found response
            return ResponseEntity.notFound().build();
        }
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/status/{status}")
    public List<TicketResponseDTO> getAllByStatus(@PathVariable String status){
        List<Ticket> ticketsByStatus = repository.findAllByStatus(status);
        List<TicketResponseDTO> ticketList = ticketsByStatus.stream().map(TicketResponseDTO::new).toList();
        return ticketList;
    }
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PatchMapping("/{id}/status")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long id) {
        Ticket ticket = repository.findById(id).get();
        if (ticket.getStatus().equals("CLOSED")) {
            ticket.setStatus("OPEN");
        } else if (ticket.getStatus().equals("OPEN")) {
            ticket.setStatus("CLOSED");
        }
        repository.save(ticket);

        return ResponseEntity.ok(ticket);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTicket(@PathVariable Long id) {
        Ticket ticket = repository.findById(id).get();
//        List<MessageResponseDTO> messageList;
//
//        messageList = messageRepository.findByTicketId(id.intValue()).stream().map(MessageResponseDTO::new).toList();
//        List<Message> message = messageRepository.findByTicketId(id.intValue());

        messageRepository.deleteByTicketId(id.intValue());
        repository.delete(ticket);

        return ResponseEntity.ok().build();
    }
}
