package org.example.lld.Calendar.repository;

import org.example.lld.Calendar.model.User;

public interface IUserRepostitory {
    User getUser(String id);
    void save(User user);

}
