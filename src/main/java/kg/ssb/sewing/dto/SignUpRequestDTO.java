package kg.ssb.sewing.dto;

import lombok.Data;


@Data
public class SignUpRequestDTO {
    private String inn;
    private String personalId;
    private String fullName;
    private String uuid;
    private String position;
    private String positionUuid;
    private String division;
    private String divisionUuid;
    private String role;
}

