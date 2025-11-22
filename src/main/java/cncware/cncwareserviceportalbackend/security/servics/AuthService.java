package cncware.cncwareserviceportalbackend.security.servics;

import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import cncware.cncwareserviceportalbackend.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public String login(String email, String rawPassword){

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(email, rawPassword)
        );

        User user = userRepository.findByEmail(email)
                .orElseThrow();

        return jwtTokenProvider.generateToken(user.getEmail());
    }

    public User register(User user){

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use.");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        return userRepository.save(user);
    }
}
