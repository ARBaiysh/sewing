package kg.ssb.sewing.dto;


import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;


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
    private String action;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeDTO that = (EmployeeDTO) o;
        return hasWorkPlace == that.hasWorkPlace
                && Objects.equals(inn, that.inn)
                && Objects.equals(personalId, that.personalId)
                && Objects.equals(dateOfBirth, that.dateOfBirth)
                && Objects.equals(fullName, that.fullName)
                && Objects.equals(residence, that.residence)
                && Objects.equals(placeOfRegistration, that.placeOfRegistration)
                && Objects.equals(uuid, that.uuid)
                && Objects.equals(position, that.position)
                && Objects.equals(positionUuid, that.positionUuid)
                && Objects.equals(division, that.division)
                && Objects.equals(divisionUuid, that.divisionUuid)
                && Objects.equals(workPlace, that.workPlace)
                && Objects.equals(workPlaceUuid, that.workPlaceUuid)
                && Objects.equals(master, that.master)
                && Objects.equals(masterUuid, that.masterUuid)
                && Objects.equals(action, that.action);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
                inn,
                personalId,
                dateOfBirth,
                fullName,
                residence,
                placeOfRegistration,
                uuid,
                position,
                positionUuid,
                division,
                divisionUuid,
                workPlace,
                workPlaceUuid,
                hasWorkPlace,
                master,
                masterUuid,
                action);
    }
}


