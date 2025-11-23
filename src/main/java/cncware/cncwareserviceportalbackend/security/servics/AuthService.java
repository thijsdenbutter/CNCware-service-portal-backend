package cncware.cncwareserviceportalbackend.security.servics;

import cncware.cncwareserviceportalbackend.exceptions.types.BusinessValidationException;
import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.models.enums.Role;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import cncware.cncwareserviceportalbackend.security.jwt.JwtTokenProvider;
import cncware.cncwareserviceportalbackend.security.payload.AuthenticationRequest;
import cncware.cncwareserviceportalbackend.security.payload.RegisterRequest;
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

    public String login(AuthenticationRequest request){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception ex) {
            throw new BusinessValidationException("Invalid credentials.");
        }

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BusinessValidationException("Invalid credentials."));

        return jwtTokenProvider.generateToken(user.getEmail());
    }

    public User register(RegisterRequest request){

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new BusinessValidationException("A user with this email already exists.");
        }

        User user = new User();
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(Role.USER);

        return userRepository.save(user);
    }
}
