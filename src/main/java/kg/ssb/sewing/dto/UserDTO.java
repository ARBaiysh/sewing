package kg.ssb.sewing.dto;


import kg.ssb.sewing.entity.enums.ERole;
import lombok.Data;


@Data
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
    private ERole role;

    public UserDTO(Long id, String inn, String personalId, String fullName, String uuid, String position, String positionUuid, String division, String divisionUuid, ERole role) {
        this.id = id;
        this.inn = inn;
        this.personalId = personalId;
        this.fullName = fullName;
        this.uuid = uuid;
        this.position = position;
        this.positionUuid = positionUuid;
        this.division = division;
        this.divisionUuid = divisionUuid;
        this.role = role;
    }
}


