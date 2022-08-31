package com.education.uopp.service;

//import com.education.uopp.registration.token.ConfirmationToken;
//import com.education.uopp.registration.token.ConfirmationTokenService;
import com.education.uopp.entity.User;
import com.education.uopp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@AllArgsConstructor
public class UserService {

//    private static final String USER_NOT_FOUND_MSG = "User with email %s not found";
//    private final UserRepository userRepository;
//
//    private final ConfirmationTokenService confirmationTokenService;
//
//    private final PasswordEncoder passwordEncoder;
//
//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        return userRepository.findByEmail(email)
//                .orElseThrow(() -> new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email)));
//    }
//
//    public String signUpUser(User user) {
//        boolean userExists = userRepository.findByEmail(user.getEmail()).isPresent();
//
//        if (userExists) {
//            User registeredUser = userRepository.findByEmail(user.getEmail()).get();
//            if (confirmationTokenService.isConfirmed(confirmationTokenService.getTokenForUser(registeredUser))) {
//                throw new IllegalStateException("Email already taken");
//            }
//        }
//        String encodedPassword = passwordEncoder.encode(user.getPassword());
//        user.setPassword(encodedPassword);
//        userSave(user);
//
//        // TODO: Send confirmation token
//        String token = UUID.randomUUID().toString();
//        ConfirmationToken confirmationToken = new ConfirmationToken(token, LocalDateTime.now(), LocalDateTime.now(), user);
//
//        confirmationTokenService.saveConfirmationToken(confirmationToken);
//
//        // TODO: SEND EMAIL
//
//
//        return token;
//    }
//
//    public void userSave(User user) {
//        userRepository.save(user);
//    }
//
//    public Integer enableUser(String email) {
//        return userRepository.enableUser(email);
//    }
}
