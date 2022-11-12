package kg.ssb.sewing.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EmployeeDetailDTO {
    private String employeeUuid;
    private String masterUuid;
    private String action;
    private String cause;
    private double rating;
    private double fine;
}
