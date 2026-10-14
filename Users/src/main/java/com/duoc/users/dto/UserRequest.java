package com.duoc.users.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {
    private String name;
    private String email;
}
