package kg.ssb.sewing.services;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.entity.enums.ERole;
import kg.ssb.sewing.entity.enums.EStatus;
import kg.ssb.sewing.exceptions.ObjExistException;
import kg.ssb.sewing.payload.request.SignUpRequest;
import kg.ssb.sewing.repository.UserRepository;
import kg.ssb.sewing.rest.RestClientUsers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;
import java.security.Principal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final RestClientUsers restClientUsers;
    private final BCryptPasswordEncoder passwordEncoder;


    public void createUser(SignUpRequest userIn) {
        if (userRepository.existsUserByPersonalId(userIn.getPersonalId())) {
            log.error("The user " + userIn.getPersonalId() + " already exist. Please check credentials");
        } else {
            User user = new User();
            user.setInn(userIn.getInn());
            user.setPersonalId(userIn.getPersonalId());
            user.setFullName(userIn.getFullName());
            user.setUuid(userIn.getUuid());
            user.setPosition(userIn.getPosition());
            user.setPositionUuid(userIn.getPositionUuid());
            user.setDivision(userIn.getDivision());
            user.setDivisionUuid(userIn.getDivisionUuid());

            user.setPassword(passwordEncoder.encode("123456"));
            if (userIn.getRole().equals("seamstress")) {
                user.getRoles().add(ERole.ROLE_SEAMSTRESS);
            } else if (userIn.getRole().equals("master")) {
                user.getRoles().add(ERole.ROLE_MASTER);
            } else {
                user.getRoles().add(ERole.ROLE_NON);
            }
            user.setStatus(EStatus.ACTIVE);
            log.info("Saving User {}", userIn.getPersonalId());
            userRepository.save(user);
        }
    }

    public User getCurrentUser(Principal principal) {
        return getUserByPrincipal(principal);
    }

    private User getUserByPrincipal(Principal principal) {
        String principalId = principal.getName();
        return userRepository.findUserByPersonalId(principalId)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found with principalId " + principalId));
    }

    public boolean getUserPasswordUnDefault(String personalId) {
        User user = userRepository.findUserByPersonalId(personalId).get();
        return !passwordEncoder.matches("123456", user.getPassword());
    }

    public User getUserById(String id) {
        return userRepository.findById(Long.parseLong(id)).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Scheduled(initialDelayString = "1000", fixedDelayString = "600000000")
    private void installUsersFor1c() throws URISyntaxException {
        restClientUsers.findUserAll().forEach(this::createUser);
    }
}
