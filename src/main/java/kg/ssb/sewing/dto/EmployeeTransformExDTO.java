package kg.ssb.sewing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmployeeTransformExDTO {

    private String employeeUuid;
    private String workPlaceUuid;
    private LocalDateTime createDateTime;
    private String authorUuid;
}
