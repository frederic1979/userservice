package io.getarrays.userservice.repo;

import io.getarrays.userservice.domain.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepo extends JpaRepository<Role,Long> {

    Optional<Role> findByName(String name);

}
