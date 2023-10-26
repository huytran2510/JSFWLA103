package fa.training.repository;

import fa.training.entities.Member;
import fa.training.entities.Role;
import fa.training.entities.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    List<Role> findByName(ERole name);
}
