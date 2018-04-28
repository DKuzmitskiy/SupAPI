package sup.service;

import sup.model.User;

public interface UserService {
    Iterable<User> getAllUsers();
    User getById(Long id);
    User add(User user);
    User update(User user);
    User delete(Long id);
}
