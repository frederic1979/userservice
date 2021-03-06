package io.getarrays.userservice.service;

import io.getarrays.userservice.domain.AppUser;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserServiceImpl implements UserService{

    private final UserRepo userRepo;
    private final RoleRepo roleRepo;

    public UserServiceImpl(UserRepo userRepo, RoleRepo roleRepo) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
    }

    @Transactional
    @Override
    public AppUser saveUser(AppUser appUser) {
        log.info("saving user {}",appUser.getName());
        return userRepo.save(appUser);
    }

    @Transactional
    @Override
    public Role saveRole(Role role) {
        log.info("saving role {}",role.getName());
        return roleRepo.save(role);
    }

    @Transactional
    @Override
    public void addRoleToUser(String username, String rolename) {
        log.info("adding role {} to user {}",rolename, username);
        Optional<AppUser> userToFind = userRepo.findByUsername(username);
        Optional<Role> roleToFind = roleRepo.findByName(rolename);
        if (userToFind.isPresent() && roleToFind.isPresent()) {
            userToFind.get().getRoles().add(roleToFind.get());
        }
    }

    @Transactional
    @Override
    public Optional<AppUser> getUser(String username) {
        log.info("get user {}",username);
        Optional<AppUser> user = userRepo.findByUsername(username);
        return user;
    }

    @Transactional
    @Override
    public List<AppUser> getUsers() {
        log.info("fetching users");
        return userRepo.findAll();
    }
}
