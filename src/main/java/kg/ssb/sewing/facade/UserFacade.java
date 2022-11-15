package kg.ssb.sewing.facade;

import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.entity.User;

public class UserFacade {
    public static UserDTO UserInUserDTO(User user) {
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
                user.getWorkPlace(),
                user.getWorkPlaceUuid(),
                user.getRoles());
    }
}
