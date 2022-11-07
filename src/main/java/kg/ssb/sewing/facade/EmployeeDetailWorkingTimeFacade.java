package kg.ssb.sewing.facade;

import kg.ssb.sewing.dto.EmployeeDetailDTOIn;
import kg.ssb.sewing.entity.EmployeeWorkingTime;

public class EmployeeDetailWorkingTimeFacade {

    public static EmployeeWorkingTime employeeDetailDTOInToEmployeeWorkingTime(EmployeeDetailDTOIn employeeDetailDTOIn) {
        return new EmployeeWorkingTime(
                employeeDetailDTOIn.getEmployeeUuid(),
                employeeDetailDTOIn.getMasterUuid(),
                employeeDetailDTOIn.getAction(),
                employeeDetailDTOIn.getCause(),
                employeeDetailDTOIn.getRating(),
                employeeDetailDTOIn.getFine()
        );
    }
}
