package io.getarrays.userservice.api;


import io.getarrays.userservice.domain.AppUser;
import io.getarrays.userservice.domain.Role;
import io.getarrays.userservice.dto.AppUserDto;
import io.getarrays.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class UserRessource {

    private final UserService userService;

    public UserRessource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    ResponseEntity<List<AppUser>> getUsers(){
        return ResponseEntity.ok(userService.getUsers());
    }

    @PostMapping("/user/save")
    ResponseEntity<AppUser> saveUser(@RequestBody AppUser user) throws URISyntaxException {
        return ResponseEntity.created(new URI("api/user/save"+userService.saveUser(user))).build();
    }

    @PostMapping("/role/save")
    ResponseEntity<Role> saveRole(@RequestBody Role role) throws URISyntaxException {
        return ResponseEntity.created(new URI("api/role/save"+userService.saveRole(role))).build();
    }


    @PostMapping("/addroletouser")
    ResponseEntity<?> addRoleToUser(@RequestBody AppUserDto appUserDto){
        userService.addRoleToUser(appUserDto.getUsername(), appUserDto.getRolename());
        return ResponseEntity.ok().build();
    }


}
