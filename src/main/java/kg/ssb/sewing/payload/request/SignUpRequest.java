package kg.ssb.sewing.payload.request;

import lombok.Data;


@Data
public class SignUpRequest {
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

