package com.education.uopp.service.impl;


import com.education.uopp.domain.entity.User;
import com.education.uopp.repository.UserRepository;
import com.education.uopp.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

@AllArgsConstructor
@Service
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.info("User is not found");
            return new UsernameNotFoundException(email);
        });
        user.setLastLoginDate(user.getLastLoginDateDisplay());
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        log.info("Returning user found by email: " + user.getEmail());
        return user;
    }
}
