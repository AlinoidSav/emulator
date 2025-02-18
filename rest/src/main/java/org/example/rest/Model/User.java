package org.example.rest.Model;

import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public class User {
    @NotBlank(message = "login is null or empty")
    private String login;
    @NotBlank(message = "password is null or empty")
    private String password;
    private String email;
    private String date;

    public User(String login,String password,String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public User(String login, String password, String email, String date) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.date = date;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "{\n\"login\": \"" + login +
                "\",\n\"password\": \"" + password +
                "\",\n\"email\": \"" + email +
                "\",\n\"date\": \"" + date + "\"\n}";
    }
}
