package kg.ssb.sewing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class EmployeeDetailExDTO {
    private Long id;
    private String employeeUuid;
    private String authorUuidStart;
    private String positionUuid;
    private LocalDateTime startDateTime;
    private String causeStart;
    private String workPlaceUuid;

    private String authorUuidStop;
    private LocalDateTime stopDateTime;
    private LocalDateTime workedHours;
    private String causeStop;
    private double rating;
    private double fine;
}
