package kg.ssb.sewing.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
public class Workplace {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String workPlace;
    private String workPlaceUuid;
    private String master;
    private String masterUuid;

    public Workplace(String workPlace, String workPlaceUuid, String master, String masterUuid) {
        this.workPlace = workPlace;
        this.workPlaceUuid = workPlaceUuid;
        this.master = master;
        this.masterUuid = masterUuid;
    }
}
