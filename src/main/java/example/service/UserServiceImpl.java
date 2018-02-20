package example.service;

import example.dao.UserDao;
import example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    public User getById(int id) {
        return userDao.getById(id);
    }

    public User add(User user) {
        return userDao.saveOrUpdate(user);
    }

    public User update(User user) {
        return userDao.saveOrUpdate(user);
    }

    public User delete(int id) {
        return userDao.delete(id);
    }
}
