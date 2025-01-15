package org.example.rest.service;

import org.example.rest.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Map;
import java.util.Random;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    Random rand = new Random();

    @Autowired
    DatabaseConnector databaseConnector;


    @GetMapping("/users")
    public ResponseEntity<?> findUser(@RequestParam(name = "login") String login) throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
        try {
            User foundeduser = databaseConnector.findUserByLogin(login);
            return new ResponseEntity<>(foundeduser, HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/1")
    public ResponseEntity<?> postLogin(@RequestBody Map<String, String> map) throws InterruptedException {
            Thread.sleep(rand.nextLong(1000, 2000));
            if (!map.containsKey("login") || !map.containsKey("password") || map.get("login").isEmpty() || map.get("password").isEmpty() || (map.size() != 3)) {
                return new ResponseEntity<>("Something was wrong", HttpStatus.BAD_REQUEST);
            } else {
                User newUser = new User(map.get("login"), map.get("password"), map.get("email"));
                return new ResponseEntity<>(databaseConnector.insertUser(newUser), HttpStatus.OK);
            }
        }


    @PostMapping("/2")
    public ResponseEntity<?> postUser(@RequestBody @Validated User user) throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
        try {
            return new ResponseEntity<>(databaseConnector.insertUser(user), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
