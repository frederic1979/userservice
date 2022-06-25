package io.getarrays.userservice.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AppUserDto {

    private String username;
    private String rolename;
}
