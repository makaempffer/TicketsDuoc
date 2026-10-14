package com.duoc.orders.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long userId;
    private Long ticketId;
}
