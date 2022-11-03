package kg.ssb.sewing.payload.request.dto;

import kg.ssb.sewing.entity.enums.ERole;
import lombok.Data;

import java.util.Set;

@Data
public class UserUpdateDTO {
    private long id;
    private String username;
    private String firstName;
    private String lastName;
    private String position;
    private String resName;
    private Set<ERole> roles;
}
