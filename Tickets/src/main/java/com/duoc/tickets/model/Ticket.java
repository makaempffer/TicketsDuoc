package com.duoc.tickets.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tickets")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long eventId;
    private String seat;
    private Double price;
    @Enumerated(EnumType.STRING)
    private TicketStatus status;
}
