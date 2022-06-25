package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.AppUser;
import io.getarrays.userservice.domain.Role;

import java.util.List;
import java.util.Optional;

public interface UserService {

    AppUser saveUser(AppUser appUser);

    Role saveRole(Role role);

    void addRoleToUser(String username, String rolename);

    Optional<AppUser> getUser(String username);

    List<AppUser> getUsers();

}
