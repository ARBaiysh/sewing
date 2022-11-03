package kg.ssb.sewing.services;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.entity.enums.ERole;
import kg.ssb.sewing.entity.enums.EStatus;
import kg.ssb.sewing.exceptions.ObjExistException;
import kg.ssb.sewing.payload.request.SignUpRequest;
import kg.ssb.sewing.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public void createUser(SignUpRequest userIn) {
        if (userRepository.existsUserByEmailOrUsername(userIn.getEmail(), userIn.getUsername())) {
            throw new ObjExistException("The user " + userIn.getUsername() + " already exist. Please check credentials");
        } else {
            User user = new User();
            user.setEmail(userIn.getEmail());
            user.setUsername(userIn.getUsername());
            user.setFirstName(userIn.getFirstName());
            user.setLastName(userIn.getLastName());
            user.setPassword(passwordEncoder.encode(userIn.getPassword()));
            user.getRoles().add(ERole.ROLE_USER);
            user.setStatus(EStatus.ACTIVE);

            log.info("Saving User {}", userIn.getEmail());
            userRepository.save(user);
        }
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String username = principal.getName();
        return userRepository.findUserByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with username " + username));
    }

    public User getUserById(String id) {
        return userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
