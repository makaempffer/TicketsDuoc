package com.duoc.events.dto;

import lombok.*;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest {
    private String name;
    private String description;
    private String venue;
    private LocalDateTime date;
    private Integer totalSeats;
}
