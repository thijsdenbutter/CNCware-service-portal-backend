package cncware.cncwareserviceportalbackend.security.servics;

import cncware.cncwareserviceportalbackend.models.entities.User;
import cncware.cncwareserviceportalbackend.repositories.UserRepository;
import cncware.cncwareserviceportalbackend.security.models.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException{

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User with email '" + email + "' not found")
                );

        return new UserPrincipal(user);
    }
}
