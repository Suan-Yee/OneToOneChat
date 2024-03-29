package com.example.websocketonetoone.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;


@Getter @Setter @Entity
@AllArgsConstructor @NoArgsConstructor @SuperBuilder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String fullName;
    private String nickName;

    @Enumerated(EnumType.STRING)
    private Status status;
}
