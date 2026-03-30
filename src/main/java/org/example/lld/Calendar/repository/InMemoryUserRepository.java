package org.example.lld.Calendar.repository;

import org.example.lld.Calendar.model.User;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements IUserRepostitory{
    private final Map<String, User> users = new HashMap<>();
    @Override
    public User getUser(String id) {
        return users.get(id);
    }

    @Override
    public void save(User user) {
        users.put(user.getId(), user);
        System.out.println("User saved!");
    }
}
