package example.dao;

import example.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();
    User getById(int id);
    User saveOrUpdate(User user);
    User delete(int id);
}
