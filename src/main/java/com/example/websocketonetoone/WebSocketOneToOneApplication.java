package com.example.websocketonetoone;

import com.example.websocketonetoone.user.Status;
import com.example.websocketonetoone.user.User;
import com.example.websocketonetoone.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class WebSocketOneToOneApplication {

    private final UserRepository userRepository;

    public static void main(String[] args) {
        SpringApplication.run(WebSocketOneToOneApplication.class, args);
        System.err.println("hello");

    }

}
