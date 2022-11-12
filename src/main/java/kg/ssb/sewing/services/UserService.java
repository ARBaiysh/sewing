package kg.ssb.sewing.services;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.entity.enums.ERole;
import kg.ssb.sewing.entity.enums.EStatus;
import kg.ssb.sewing.dto.LoginRequestDTO;
import kg.ssb.sewing.dto.SignUpRequestDTO;
import kg.ssb.sewing.repository.UserRepository;
import kg.ssb.sewing.rest.Rest1cClientUser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserService {
    private final UserRepository userRepository;
    private final Rest1cClientUser rest1CClientUser;
    private final BCryptPasswordEncoder passwordEncoder;


    public void createUser(SignUpRequestDTO userIn, String password) {
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

            user.setPassword(passwordEncoder.encode(password));
            if (userIn.getRole().equals("seamstress")) {
                user.getRoles().add(ERole.ROLE_SEAMSTRESS);
            } else if (userIn.getRole().equals("master")) {
                user.getRoles().add(ERole.ROLE_MASTER);
            } else if (userIn.getRole().equals("masters_leader")) {
                user.getRoles().add(ERole.ROLE_MASTERS_LEADER);
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

    public boolean existsUserByPersonalId(String personalId) {
        return userRepository.existsUserByPersonalId(personalId);
    }

    public boolean existsUserBy1CBases(String username) {
        ResponseEntity<SignUpRequestDTO> userByPersonalId = rest1CClientUser.getUserByPersonalId(username);
        SignUpRequestDTO user = userByPersonalId.getBody();
        assert user != null;
        return !user.getUuid().isEmpty();
    }

    public void addNewUser(LoginRequestDTO loginRequest) {
        SignUpRequestDTO newUser = rest1CClientUser.getUserByPersonalId(loginRequest.getUsername()).getBody();
        assert newUser != null;
        this.createUser(newUser, loginRequest.getPassword());
    }
}
