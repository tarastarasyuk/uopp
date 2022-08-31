package com.education.uopp.registration.token;

//import com.education.uopp.entity.User;
//import lombok.AllArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.Optional;

//@Service
//@AllArgsConstructor
public class ConfirmationTokenService {

//    private final ConfirmationTokenRepository confirmationTokenRepository;
//
//    public void saveConfirmationToken(ConfirmationToken token) {
//        confirmationTokenRepository.save(token);
//    }
//
//    public Optional<ConfirmationToken> getToken(String token) {
//        return confirmationTokenRepository.findByToken(token);
//    }
//
//    public Integer setConfirmedAt(String token) {
//        return confirmationTokenRepository.updateConfirmedAt(token, LocalDateTime.now());
//    }
//
//    public String getTokenForUser(User user) {
//        return confirmationTokenRepository.findConfirmationTokenByUser(user).get().getToken();
//    }
//    public Boolean isConfirmed(String token) {
//        return confirmationTokenRepository.existsConfirmationTokenByTokenAndConfirmedAtTrue(token);
//    }
}
