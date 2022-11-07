package kg.ssb.sewing.services;


import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.entity.EmployeeWorkingTime;
import kg.ssb.sewing.repository.EmployeeDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeDetailService {
    private final EmployeeDetailRepository employeeDetailRepository;


    public EmployeeDetail saveEmployeeDetailWorkingTime(EmployeeWorkingTime employeeWorkingTime) {
        String employeeUuid = employeeWorkingTime.getEmployeeUuid();
        if (employeeDetailRepository.existsEmployeeDetailByEmployeeUuid(employeeUuid)) {
            EmployeeDetail employeeDetail = employeeDetailRepository.findById(employeeUuid).get();
            employeeDetail.getWorkingTimeList().add(employeeWorkingTime);
            return employeeDetailRepository.save(employeeDetail);
        } else {
            EmployeeDetail employeeDetail = new EmployeeDetail();
            employeeDetail.setEmployeeUuid(employeeUuid);
            employeeDetail.getWorkingTimeList().add(employeeWorkingTime);
            return employeeDetailRepository.save(employeeDetail);
        }
    }

    public EmployeeDetail getEmployeeDetailByEmployeeUuid(String employeeUuid) {
        return employeeDetailRepository.findById(employeeUuid).orElse(new EmployeeDetail());
    }
}
