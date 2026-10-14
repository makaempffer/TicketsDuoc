package com.duoc.events.service;

import com.duoc.events.dto.EventRequest;
import com.duoc.events.model.Event;
import com.duoc.events.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<Event> findAll() {
        return eventRepository.findAll();
    }

    public Optional<Event> findById(Long id) {
        return eventRepository.findById(id);
    }

    public Event create(EventRequest request) {
        Event event = Event.builder()
                .name(request.getName())
                .description(request.getDescription())
                .venue(request.getVenue())
                .date(request.getDate())
                .totalSeats(request.getTotalSeats())
                .availableSeats(request.getTotalSeats())
                .build();
        return eventRepository.save(event);
    }
}
