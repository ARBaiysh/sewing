package kg.ssb.sewing.dto;


import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class LeaderDto {
    private String inn;
    private String personalId;
    private String dateOfBirth;
    private String fullName;
    private String residence;
    private String placeOfRegistration;
    private String uuid;
    private String workPlace;
    private String workPlaceUuid;
}


