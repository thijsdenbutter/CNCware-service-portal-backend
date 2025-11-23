package cncware.cncwareserviceportalbackend.security.controllers;

import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.security.jwt.JwtTokenProvider;
import cncware.cncwareserviceportalbackend.security.payload.AuthenticationRequest;
import cncware.cncwareserviceportalbackend.security.payload.AuthenticationResponse;
import cncware.cncwareserviceportalbackend.security.payload.RegisterRequest;
import cncware.cncwareserviceportalbackend.security.servics.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        String token = authService.login(request);

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        User user = authService.register(request);
        String token = jwtTokenProvider.generateToken(user.getEmail());

        return ResponseEntity.ok(new AuthenticationResponse(token));
    }
}
