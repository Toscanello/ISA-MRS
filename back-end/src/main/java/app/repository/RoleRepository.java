package app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    List<Role> findByName(String name);
}
