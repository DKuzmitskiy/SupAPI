package sup.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sup.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {
    User findByName(String name);
}
