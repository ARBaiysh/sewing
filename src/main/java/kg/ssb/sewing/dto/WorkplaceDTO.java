package kg.ssb.sewing.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class WorkplaceDTO {
    private String workPlace;
    private String workPlaceUuid;
    private String master;
    private String masterUuid;
}
