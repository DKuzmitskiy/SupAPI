package example.dao;

import example.model.User;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    public List<User> getAllUsers() {
        Criteria criteria = getSession().createCriteria(User.class);
        return criteria.list();
    }

    public User getById(int id) {
        return (User) getSession().get(User.class, id);
    }

    public User saveOrUpdate(User user) {
        getSession().saveOrUpdate(user);
        return user;
    }


    public User delete(int id) {
        User user = getById(id);
        getSession().delete(user);
        return user;
    }
}
