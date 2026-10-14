package com.duoc.users.service;

import com.duoc.users.dto.UserRequest;
import com.duoc.users.model.User;
import com.duoc.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    public User create(UserRequest request) {
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .createdAt(LocalDateTime.now())
                .build();
        return userRepository.save(user);
    }
}
