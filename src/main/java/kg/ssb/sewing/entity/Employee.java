package kg.ssb.sewing.entity;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Employee {
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

}


