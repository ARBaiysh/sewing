package kg.ssb.sewing.dto;

import lombok.Data;

@Data
public class EmployeeUpdateWorkPlaceUuidDTO {
    private String uuid;
    private String workPlace;
    private String workPlaceUuid;
    private String master;
    private String masterUuid;
}
