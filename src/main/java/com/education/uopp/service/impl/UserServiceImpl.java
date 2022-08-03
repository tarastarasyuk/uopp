package com.education.uopp.service.impl;

import com.education.uopp.entity.Role;
import com.education.uopp.entity.User;
import com.education.uopp.repository.UserRepository;
import com.education.uopp.service.UserService;
import com.education.uopp.validator.ConfirmedUserValidator;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ConfirmedUserValidator confirmedUserValidator;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = findUserByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User %s not found", email)));
        if (!confirmedUserValidator.test(user)) {
            throw new IllegalStateException("User was not confirmed");
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), mapRolesToAuthorities(user.getRole()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
