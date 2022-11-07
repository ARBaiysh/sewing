package kg.ssb.sewing.controller;

import kg.ssb.sewing.payload.request.LoginRequest;
import kg.ssb.sewing.payload.request.SearchUserRequest;
import kg.ssb.sewing.payload.response.JWTTokenSuccessResponse;
import kg.ssb.sewing.security.JWTTokenProvider;
import kg.ssb.sewing.security.SecurityConstants;
import kg.ssb.sewing.services.UserService;
import kg.ssb.sewing.validations.ResponseErrorValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
@PreAuthorize(value = "permitAll()")
@RequiredArgsConstructor
@CrossOrigin()
public class AuthController {
    private final JWTTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;
    private final ResponseErrorValidation responseErrorValidation;
    private final UserService userService;


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        ResponseEntity<?> errors = responseErrorValidation.mapValidationService(bindingResult);
        if (!ObjectUtils.isEmpty(errors)) return errors;
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginRequest.getUsername(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = SecurityConstants.TOKEN_PREFIX + jwtTokenProvider.generateToken(authentication);

        return ResponseEntity.ok(new JWTTokenSuccessResponse(userService.getUserPasswordUnDefault(loginRequest.getUsername()), jwt));
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> signUpUser(@Valid @RequestBody LoginRequest loginRequest) {
        userService.addNewUser(loginRequest);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<?> searchUser(@Valid @RequestBody SearchUserRequest searchUserRequest) {
        if (userService.existsUserByPersonalId(searchUserRequest.getUsername())) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else if (userService.existsUserBy1CBases(searchUserRequest.getUsername())) {
            return new ResponseEntity<>(HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
}


