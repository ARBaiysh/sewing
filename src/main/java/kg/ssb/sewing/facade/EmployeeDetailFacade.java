package kg.ssb.sewing.facade;

import kg.ssb.sewing.dto.EmployeeDetailDTOIn;
import kg.ssb.sewing.entity.EmployeeDetail;

public class EmployeeDetailFacade {

    public static EmployeeDetail employeeDetailDTOInToEmployeeDetail(EmployeeDetailDTOIn employeeDetailDTOIn) {
        return new EmployeeDetail(
                employeeDetailDTOIn.getEmployeeUuid(),
                employeeDetailDTOIn.getMasterUuid(),
                employeeDetailDTOIn.getAction(),
                employeeDetailDTOIn.getCause(),
                employeeDetailDTOIn.getRating(),
                employeeDetailDTOIn.getFine()
        );
    }
}
