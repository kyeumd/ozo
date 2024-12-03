package com.ozo.security.service;

import com.ozo.security.domain.dto.UserContext;
import com.ozo.security.domain.dto.UserDto;
import com.ozo.user.infra.UserRepository;
import com.ozo.user.model.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByCreatorUserId(userId);
        UserDto userDto = user.map(UserDto::from)
                              .orElseThrow(() -> new UsernameNotFoundException("No user found user ID : " + userId));
        List<GrantedAuthority> authorities = new ArrayList<>();

        return new UserContext(userDto, authorities);
    }

}
