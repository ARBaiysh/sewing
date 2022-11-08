package kg.ssb.sewing.services;


import kg.ssb.sewing.dto.EmployeeDetailDTOIn;
import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.facade.EmployeeDetailFacade;
import kg.ssb.sewing.repository.EmployeeDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeDetailService {
    private final EmployeeDetailRepository employeeDetailRepository;


    public EmployeeDetail getEmployeeDetailByEmployeeUuid(EmployeeDetailDTOIn employeeDetailDTOIn) {
        if (!employeeDetailDTOIn.getAction().isEmpty()) {
            EmployeeDetail employeeDetail = EmployeeDetailFacade.employeeDetailDTOInToEmployeeDetail(employeeDetailDTOIn);
            employeeDetail.setDateTime(LocalDateTime.now());
            return employeeDetailRepository.save(employeeDetail);
        } else {
            return employeeDetailRepository.findFirstByOrderByIdDesc();
        }
    }

    public List<EmployeeDetail> getAllEmployeeDetails(String employeeUuid) {
        return employeeDetailRepository.findAllByEmployeeUuidOrderByIdDesc(employeeUuid);
    }
}
