package kg.ssb.sewing.dto;

import kg.ssb.sewing.entity.EmployeeWorkingTime;
import lombok.Data;

import java.util.List;

@Data
public class EmployeeDetailDTOOut {
    private String uuid;
    private String masterUuid;
    private String action;
    private double rating;
    private double fine;
    private List<EmployeeWorkingTime> employeeWorkingTimes;
}
