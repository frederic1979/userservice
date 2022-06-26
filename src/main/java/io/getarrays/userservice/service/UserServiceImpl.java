package io.getarrays.userservice.service;
import io.getarrays.userservice.domain.AppUser;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.repo.RoleRepo;
import io.getarrays.userservice.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements UserService, UserDetailsService {


    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(@Lazy UserRepo userRepo,@Lazy RoleRepo roleRepo, @Lazy PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<AppUser> user = userRepo.findByUsername(username);
        if (user.isEmpty()) {
            log.error("user not found in the DATABASE");
            throw new UsernameNotFoundException("user not found in the DATABASE");
        } else {
            log.info("User found in the DB {}", username);
        }

        List<SimpleGrantedAuthority> authorities = user.get().getRoles()
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(username, user.get().getPassword(), authorities);
    }

    @Transactional
    @Override
    public AppUser saveUser(AppUser appUser) {
        appUser.setPassword(passwordEncoder.encode(appUser.getPassword()));
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
