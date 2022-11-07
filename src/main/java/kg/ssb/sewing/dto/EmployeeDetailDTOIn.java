package kg.ssb.sewing.dto;

import lombok.Data;

@Data
public class EmployeeDetailDTOIn {
    private String employeeUuid;
    private String masterUuid;
    private String action;
    private String cause;
    private double rating;
    private double fine;
}
