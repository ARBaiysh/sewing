package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api/user")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER','ROLE_HEAD_OF_CUTTING')")
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        log.info("Start getCurrentUser principal = {}", principal.getName());
        UserDTO user = userService.getCurrentUser(principal);
        log.info("Finish getCurrentUser user name = {}", user.getFullName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/update")
    public ResponseEntity<UserDTO> currentUserUpdate(Principal principal) {
        UserDTO user = userService.currentUserUpdate(principal);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
