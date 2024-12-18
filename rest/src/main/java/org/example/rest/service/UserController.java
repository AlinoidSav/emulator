package org.example.rest.service;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.apache.coyote.BadRequestException;
import org.example.rest.Model.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    Random rand = new Random();

    @GetMapping
    public String getStaticRequest() throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
        return "{\"message\": \"This is a static JSON response.\"}";
    }

    @PostMapping("/1")
    public ResponseEntity<?> postLogin(@RequestBody Map<String, String> map) throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
        if (!map.containsKey("login") || !map.containsKey("password")) {
            return new ResponseEntity<>("The entered keys are not correct", HttpStatus.BAD_REQUEST);
        } else {
            if (map.get("login").isEmpty() || map.get("password").isEmpty()) {
                return new ResponseEntity<>("The entered values are empty", HttpStatus.BAD_REQUEST);
            } else {
                if ((map.size() != 2)) {
                    return new ResponseEntity<>("The number of pairs is not correct", HttpStatus.BAD_REQUEST);
                } else {
                    if (map.containsKey("password") && map.containsKey("login")) {
                        User user = new User(map.get("login"), map.get("password"));
                        return new ResponseEntity<>(user, HttpStatus.OK);
                    }
                }
                return null;
            }
        }
    }

    @PostMapping("/2")
    public ResponseEntity<?> postUser(@RequestBody @Validated User user) throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
//        user.setDate(LocalDateTime.now());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

}
