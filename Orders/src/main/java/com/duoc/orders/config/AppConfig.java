package com.duoc.orders.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class AppConfig {
    @Value("${users.service.url}")
    private String usersServiceUrl;

    @Value("${tickets.service.url}")
    private String ticketsServiceUrl;

    @Bean
    public RestClient usersClient() {
        return RestClient.builder().baseUrl(usersServiceUrl).build();
    }

    @Bean
    public RestClient ticketsClient() {
        return RestClient.builder().baseUrl(ticketsServiceUrl).build();
    }
}
