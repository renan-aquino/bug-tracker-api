package com.api.bugtracker.models;

import com.api.bugtracker.dtos.TicketRequestDTO;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Table(name = "tickets")
@Entity(name = "tickets")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Ticket {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private LocalDateTime created_at;
    public String status;

    public Ticket(TicketRequestDTO data){
        this.title = data.title();
    }

}
