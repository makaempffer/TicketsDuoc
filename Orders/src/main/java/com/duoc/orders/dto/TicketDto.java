package com.duoc.orders.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TicketDto {
    private Long id;
    private Long eventId;
    private String seat;
    private Double price;
    private String status;
}
