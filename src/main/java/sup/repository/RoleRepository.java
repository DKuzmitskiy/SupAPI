package sup.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import sup.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
