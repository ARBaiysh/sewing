package kg.ssb.sewing.services;


import kg.ssb.sewing.dto.EmployeeDetailDTO;
import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.repository.EmployeeDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeDetailService {
    private final EmployeeDetailRepository employeeDetailRepository;
    private final ModelMapper modelMapper;


    public EmployeeDetail getEmployeeDetailByEmployeeUuid(EmployeeDetailDTO employeeDetailDTO) {
        if (!employeeDetailDTO.getAction().isEmpty()) {
            EmployeeDetail employeeDetail = modelMapper.map(employeeDetailDTO, EmployeeDetail.class);
            employeeDetail.setDateTime(LocalDateTime.now());
            return employeeDetailRepository.save(employeeDetail);
        } else {
            return employeeDetailRepository.findFirstByOrderByIdDesc();
        }
    }

    public List<EmployeeDetail> getAllEmployeeDetailsData(String employeeUuid) {
        LocalDateTime now = LocalDateTime.now();
        return employeeDetailRepository.findAllByEmployeeUuidAndDateTimeBetween(employeeUuid, now.toLocalDate().atStartOfDay(), now.toLocalDate().atTime(LocalTime.MAX));
    }

    public List<EmployeeDetail> getAllEmployeeDetails(String employeeUuid) {
        return employeeDetailRepository.findAllByEmployeeUuidOrderByIdDesc(employeeUuid);
    }

}
