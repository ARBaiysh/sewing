package kg.ssb.sewing.payload.request.dto;


import kg.ssb.sewing.entity.enums.ERole;
import kg.ssb.sewing.entity.enums.EStatus;
import lombok.Data;

import java.util.Set;

@Data
public class UserDTO {
    private Long id;
    private String email;
    private String username;
    private String firstName;
    private String lastName;
    private String position;
    private String resName;
    private EStatus status;
    private Set<ERole> roles;
}


