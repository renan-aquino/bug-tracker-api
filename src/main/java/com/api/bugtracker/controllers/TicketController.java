package com.api.bugtracker.controllers;

import com.api.bugtracker.dtos.LoginResponseDTO;
import com.api.bugtracker.dtos.MessageRequestDTO;
import com.api.bugtracker.models.Message;
import com.api.bugtracker.repositories.MessageRepository;
import com.api.bugtracker.repositories.TicketRepository;
import com.api.bugtracker.dtos.TicketRequestDTO;
import com.api.bugtracker.dtos.TicketResponseDTO;
import com.api.bugtracker.models.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

        MessageRequestDTO messageDTO = new MessageRequestDTO(data.text());

        Message message = new Message(messageDTO);
        message.setTicketId(Math.toIntExact(ticketData.getId()));
        message.setCreated_at(LocalDateTime.now().withNano(0));
        message.setUser_id(1);
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
}
