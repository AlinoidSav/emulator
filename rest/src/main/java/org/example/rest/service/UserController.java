package org.example.rest.service;

import org.example.rest.Model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Random;

@RestController
@RequestMapping("/api")
@Validated
public class UserController {

    Random rand = new Random();

    @Autowired
    DatabaseConnector databaseConnector;


    @GetMapping("/users")
    public ResponseEntity<?> findUser(@RequestParam(name = "login") String login) throws InterruptedException, SQLException {
        Thread.sleep(rand.nextLong(1000, 2000));
        return databaseConnector.findUserByLogin(login);
    }


    @PostMapping("/2")
    public ResponseEntity<?> postUser(@RequestBody User user) throws InterruptedException {
        Thread.sleep(rand.nextLong(1000, 2000));
        try {
            return databaseConnector.insertUser(user);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
