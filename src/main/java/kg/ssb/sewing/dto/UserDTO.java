package kg.ssb.sewing.dto;


import kg.ssb.sewing.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private String inn;
    private String personalId;
    private String fullName;
    private String uuid;
    private String position;
    private String positionUuid;
    private String division;
    private String divisionUuid;
    private String workPlace;
    private String workPlaceUuid;
    private ERole role;
}


