package com.example.websocketonetoone.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByStatus(Status status);
    Optional<User> findByNickName(String nickName);

}
