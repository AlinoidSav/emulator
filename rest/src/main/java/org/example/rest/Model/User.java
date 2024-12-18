package org.example.rest.Model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.validation.annotation.Validated;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

//@Getter
//@Setter
@Validated
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @NotBlank(message = "login is null or empty")
    private String login;
    @NotBlank(message = "password is null or empty")
    private String password;
    private String date;
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public User(String login, String password) {
        this.login = login;
        this.password = password;
        this.date = formatter.format(LocalDateTime.now());
    }

    public String getLogin() {
        return login;
    }

    public String getDate() {
        return date;
    }

    public String getPassword() {
        return password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "User{" +
                "login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", date=" + date +
                '}';
    }
}
