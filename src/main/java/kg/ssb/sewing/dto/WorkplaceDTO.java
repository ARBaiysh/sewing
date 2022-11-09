package kg.ssb.sewing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WorkplaceDTO {
    private String workPlace;
    private String workPlaceUuid;
    private String master;
    private String masterUuid;
}
