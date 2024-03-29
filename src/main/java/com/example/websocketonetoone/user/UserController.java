package com.example.websocketonetoone.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {

    private final UserService userService;

    @MessageMapping("/user.addUser")
    @SendTo("/user/public")
    public User addUser(@Payload User user){
        userService.saveUser(user);
        return user;
    }
    @MessageMapping("/user.disconnectUser")
    @SendTo("/user/public")
    public User disconnectUser(
            @Payload User user
    ) {
        userService.disconnect(user);
        return user;
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> findConnectedUsers() {
        log.info("Connected users {}",userService.findConnectedUser());
        return ResponseEntity.ok(userService.findConnectedUser());
    }

    @GetMapping("/register")
    public String viewPage(Model model){
        model.addAttribute("user",new User());
        log.info("Hello this is register");
        return "loginPage";
    }
    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user")User user){
        user.setStatus(Status.OFFLINE);
        userService.saveUser(user);
        return "redirect:/";
    }

}
