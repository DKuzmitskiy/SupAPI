package example.service;

import example.model.User;

import java.util.List;

public interface UserService {
    List<User> getAllUsers();
    User getById(int id);
    User add(User user);
    User update(User user);
    User delete(int id);
}
