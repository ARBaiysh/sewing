package kg.ssb.sewing.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class EmployeeDTO {
    private String inn;
    private String personalId;
    private String dateOfBirth;
    private String fullName;
    private String residence;
    private String placeOfRegistration;
    private String uuid;
    private String position;
    private String positionUuid;
    private String division;
    private String divisionUuid;
    private String workPlace;
    private String workPlaceUuid;
    private boolean hasWorkPlace;
    private String master;
    private String masterUuid;
}


