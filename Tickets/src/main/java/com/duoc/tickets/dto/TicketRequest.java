package com.duoc.tickets.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketRequest {
    private Long eventId;
    private String seat;
    private Double price;
}
