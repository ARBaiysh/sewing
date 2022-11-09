package kg.ssb.sewing.entity;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Bant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
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

    public Bant(String inn, String personalId, String dateOfBirth, String fullName, String residence,
                String placeOfRegistration, String uuid, String position, String positionUuid, String division,
                String divisionUuid, String workPlace, String workPlaceUuid, boolean hasWorkPlace, String master,
                String masterUuid) {
        this.inn = inn;
        this.personalId = personalId;
        this.dateOfBirth = dateOfBirth;
        this.fullName = fullName;
        this.residence = residence;
        this.placeOfRegistration = placeOfRegistration;
        this.uuid = uuid;
        this.position = position;
        this.positionUuid = positionUuid;
        this.division = division;
        this.divisionUuid = divisionUuid;
        this.workPlace = workPlace;
        this.workPlaceUuid = workPlaceUuid;
        this.hasWorkPlace = hasWorkPlace;
        this.master = master;
        this.masterUuid = masterUuid;
    }
}


