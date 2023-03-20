package com.pamela.todoapp.security;

import com.pamela.todoapp.dto.User;
import com.pamela.todoapp.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userService.getUser(username)
                .orElseThrow(() -> new UsernameNotFoundException("User does not exist"));

        return new AppUserPrincipal(user);
    }
}
