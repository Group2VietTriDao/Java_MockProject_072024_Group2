package com.group2.securityguardrentalmanagement.viewmodel;

import com.group2.securityguardrentalmanagement.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserViewModel {

    private List<User> users;

    public UserViewModel() {
        this.users = new ArrayList<>();
        User user = new User(1L, "John Doe", "john.doe@example.com");
        this.users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }
}