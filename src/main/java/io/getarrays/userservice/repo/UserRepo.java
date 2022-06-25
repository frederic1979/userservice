package io.getarrays.userservice.repo;

import io.getarrays.userservice.domain.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<AppUser,Long>  {

    Optional<AppUser> findByUsername(String username);

}
