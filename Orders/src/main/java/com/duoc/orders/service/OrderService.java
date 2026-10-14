package com.duoc.orders.service;

import com.duoc.orders.dto.OrderRequest;
import com.duoc.orders.dto.TicketDto;
import com.duoc.orders.dto.UserDto;
import com.duoc.orders.model.Order;
import com.duoc.orders.model.OrderStatus;
import com.duoc.orders.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final RestClient usersClient;
    private final RestClient ticketsClient;

    public OrderService(OrderRepository orderRepository,
                        @Qualifier("usersClient") RestClient usersClient,
                        @Qualifier("ticketsClient") RestClient ticketsClient) {
        this.orderRepository = orderRepository;
        this.usersClient = usersClient;
        this.ticketsClient = ticketsClient;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Optional<Order> findById(Long id) {
        return orderRepository.findById(id);
    }

    public Order create(OrderRequest request) {
        usersClient.get()
                .uri("/users/{id}", request.getUserId())
                .retrieve()
                .body(UserDto.class);

        TicketDto ticket = ticketsClient.get()
                .uri("/tickets/{id}", request.getTicketId())
                .retrieve()
                .body(TicketDto.class);

        ticketsClient.put()
                .uri("/tickets/{id}/reserve", request.getTicketId())
                .retrieve()
                .toBodilessEntity();

        Order order = Order.builder()
                .userId(request.getUserId())
                .ticketId(request.getTicketId())
                .totalPrice(ticket.getPrice())
                .status(OrderStatus.PENDING)
                .createdAt(LocalDateTime.now())
                .build();
        return orderRepository.save(order);
    }

    public Optional<Order> cancel(Long id) {
        return orderRepository.findById(id).map(order -> {
            order.setStatus(OrderStatus.CANCELLED);
            return orderRepository.save(order);
        });
    }
}
