package com.szsleedongkyeum.security.domain.dto;

import java.util.List;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
public class UserContext implements UserDetails {

    private UserDto userDto;
    private final List<GrantedAuthority> roles;

    public UserContext(UserDto userDto, List<GrantedAuthority> roles) {
        this.userDto = userDto;
        this.roles = roles;
    }

    @Override
    public List<GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return userDto.getPassword();
    }

    @Override
    public String getUsername() {
        return userDto.getUserId();
    }

}
