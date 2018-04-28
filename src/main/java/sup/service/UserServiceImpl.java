package sup.service;

import org.apache.commons.codec.digest.DigestUtils;
import sup.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sup.repository.UserRepository;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return (List<User>) userRepository.findAll();
    }

    public User getById(Long id) {
        return userRepository.findOne(id);
    }

    public User add(User user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        return userRepository.save(user);
    }

    public User update(User user) {
        user.setPassword(DigestUtils.sha256Hex(user.getPassword()));
        return userRepository.save(user);
    }

    public User delete(Long id) {
        User user = userRepository.findOne(id);
        userRepository.delete(id);
        return user;
    }
}
