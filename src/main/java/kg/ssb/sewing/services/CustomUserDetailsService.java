package kg.ssb.sewing.services;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String personalId) {
        User user = userRepository.findUserByPersonalId(personalId)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username: " + personalId));
        return build(user);
    }

    public static User build(User user) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getRoles().name()));

        return new User(
                user.getId(),
                user.getInn(),
                user.getPersonalId(),
                user.getFullName(),
                user.getUuid(),
                user.getPosition(),
                user.getPositionUuid(),
                user.getDivision(),
                user.getDivisionUuid(),
                user.getPassword(),
                authorities);
    }
}
