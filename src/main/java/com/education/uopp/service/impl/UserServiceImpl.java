package com.education.uopp.service.impl;


import com.education.uopp.domain.entity.User;
import com.education.uopp.repository.UserRepository;
import com.education.uopp.service.UserService;
import com.education.uopp.security.brutforce.LoginAttemptService;
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
    private LoginAttemptService loginAttemptService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.info("User is not found");
            return new UsernameNotFoundException(email);
        });
        validateLoginAttempt(user);
        user.setLastLoginDate(user.getLastLoginDateDisplay());
        user.setLastLoginDate(new Date());
        userRepository.save(user);
        log.info("Returning user found by email: " + user.getEmail());
        return user;
    }

    private void validateLoginAttempt(User user) {
        // TODO: recheck the flow - try to simplify
        if (user.isAccountNonLocked()) {
            if (loginAttemptService.hasExceededMaxAttempts(user.getUsername())) {
                user.setLocked(true);
            }
        } else {
            if (!loginAttemptService.isStillLocked(user)) {
                loginAttemptService.evictUserFromLoginAttemptCache(user.getUsername());
                user.setLocked(false);
            }
        }
    }
}
