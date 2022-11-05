package kg.ssb.sewing.controller;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.facade.UserFacade;
import kg.ssb.sewing.payload.request.dto.UserDTO;
import kg.ssb.sewing.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("api/user")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    @PreAuthorize("hasRole('SEAMSTRESS')")
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        User user = userService.getCurrentUser(principal);
        return new ResponseEntity<>(UserFacade.UserInUserDTO(user), HttpStatus.OK);
    }

    @GetMapping("/id/{userId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<User> getUserProfile(@PathVariable String userId) {
        User user = userService.getUserById(userId);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
