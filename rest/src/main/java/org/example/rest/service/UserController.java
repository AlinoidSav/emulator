package org.example.rest.service;

import org.example.rest.Model.User;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class UserController {

    Random rand = new Random();

    @GetMapping
    public String getStaticRequest() throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
        return "{\"message\": \"This is a static JSON response.\"}";
    }

    @PostMapping
    public User postLogin(@RequestBody User user) throws InterruptedException {
        user.setDate(LocalDateTime.now());
        Thread.sleep(rand.nextLong(1000, 2000));
        return user;
    }
}
