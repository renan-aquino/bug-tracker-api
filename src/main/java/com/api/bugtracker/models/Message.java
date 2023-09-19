package com.api.bugtracker.models;

import com.api.bugtracker.dtos.MessageRequestDTO;
import com.api.bugtracker.dtos.TicketRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "messages")
@Entity(name = "messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Message {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String text;
    private LocalDateTime created_at;
    private String user_id;
    private Integer ticketId;

    public Message(MessageRequestDTO data){
        this.text = data.text();
    }

}
