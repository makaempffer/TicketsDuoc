package com.duoc.tickets.controller;

import com.duoc.tickets.dto.TicketRequest;
import com.duoc.tickets.model.Ticket;
import com.duoc.tickets.service.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/tickets")
@RequiredArgsConstructor
public class TicketController {
    private final TicketService ticketService;

    @GetMapping
    public List<Ticket> findAll() {
        return ticketService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ticket> findById(@PathVariable Long id) {
        return ticketService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Ticket> create(@RequestBody TicketRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.create(request));
    }

    @PutMapping("/{id}/reserve")
    public ResponseEntity<Ticket> reserve(@PathVariable Long id) {
        return ticketService.reserve(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/sell")
    public ResponseEntity<Ticket> sell(@PathVariable Long id) {
        return ticketService.sell(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
