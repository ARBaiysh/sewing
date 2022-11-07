package kg.ssb.sewing.facade;

import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.dto.UserDTO;

public class UserFacade {
    public static UserDTO UserInUserDTO(User user){
        return new UserDTO(
                user.getId(),
                user.getInn(),
                user.getPersonalId(),
                user.getFullName(),
                user.getUuid(),
                user.getPosition(),
                user.getPositionUuid(),
                user.getDivision(),
                user.getDivisionUuid(),
                user.getRoles().iterator().next());
    }
}
