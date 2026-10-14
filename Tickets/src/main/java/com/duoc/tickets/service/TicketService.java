package com.duoc.tickets.service;

import com.duoc.tickets.dto.TicketRequest;
import com.duoc.tickets.model.Ticket;
import com.duoc.tickets.model.TicketStatus;
import com.duoc.tickets.repository.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TicketService {
    private final TicketRepository ticketRepository;

    public List<Ticket> findAll() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> findById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket create(TicketRequest request) {
        Ticket ticket = Ticket.builder()
                .eventId(request.getEventId())
                .seat(request.getSeat())
                .price(request.getPrice())
                .status(TicketStatus.AVAILABLE)
                .build();
        return ticketRepository.save(ticket);
    }

    public Optional<Ticket> reserve(Long id) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setStatus(TicketStatus.RESERVED);
            return ticketRepository.save(ticket);
        });
    }

    public Optional<Ticket> sell(Long id) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setStatus(TicketStatus.SOLD);
            return ticketRepository.save(ticket);
        });
    }
}
