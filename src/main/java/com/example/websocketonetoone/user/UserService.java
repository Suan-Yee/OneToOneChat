package com.example.websocketonetoone.user;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void saveUser(User user){
        user.setStatus(Status.ONLINE);
        userRepository.save(user);
    }
    public void disconnect(User user){
        var storedUser = userRepository.findById(user.getId()).orElse(null);
        if(storedUser != null){
            storedUser.setStatus(Status.OFFLINE);
            userRepository.save(storedUser);
        }
    }
    public User findById(Long userId){
        User user  =userRepository.findById(userId).orElse(null);
        return user;
    }
    public List<User> findConnectedUser(){
        return userRepository.findAllByStatus(Status.ONLINE);
    }
}
