package kg.ssb.sewing.facade;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.payload.request.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserFacade {
    public static UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUsername(user.getUsername());
        userDTO.setStatus(user.getStatus());
        userDTO.setRoles(user.getRoles());
        return userDTO;
    }

}
