package kg.ssb.sewing.services;


import kg.ssb.sewing.dto.EmployeeTransformExDTO;
import kg.ssb.sewing.dto.ResponseDTO;
import kg.ssb.sewing.entity.EmployeeTransformEx;
import kg.ssb.sewing.repository.EmployeeTransformExRepository;
import kg.ssb.sewing.rest.Rest1cClientEmployeeTransformEx;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeTransformExService {
    private final EmployeeTransformExRepository employeeTransformExRepository;
    private final Rest1cClientEmployeeTransformEx rest1cClientEmployeeTransformEx;
    private final ModelMapper modelMapper;

    public void saveEmployeeTransformEx(EmployeeTransformEx employeeTransformEx) {
        employeeTransformExRepository.save(employeeTransformEx);
    }

    public ResponseDTO sendTo1cEmployeeTransformEx() {
        LocalDateTime now = LocalDateTime.now();
        List<EmployeeTransformEx> employeeTransformExList = employeeTransformExRepository
                .findAllByCreateDateTimeBetween(now.toLocalDate().atStartOfDay(), now.toLocalDate().atTime(LocalTime.MAX))
                .orElse(new ArrayList<>());
        log.info("Start sendTo1cEmployeeTransformEx total - {}", employeeTransformExList.size());

        if (!employeeTransformExList.isEmpty()) {
            List<EmployeeTransformExDTO> employeeDetailExDTOS = employeeTransformExList
                    .stream()
                    .map(employeeTransformEx -> modelMapper.map(employeeTransformEx, EmployeeTransformExDTO.class))
                    .collect(Collectors.toList());

            ResponseDTO body = rest1cClientEmployeeTransformEx.exportEmployeeDetailDTO(employeeDetailExDTOS).getBody();

            log.info("Finish sendTo1cEmployeeTransformEx, body message - {}", body.getMessage());
            log.info("Start update EmployeeTransformEx status code");
            if (body.getMessage().equals("OK")) {
                employeeTransformExList.forEach(em -> em.setStatusTransferIn1C(200));
                employeeTransformExRepository.saveAll(employeeTransformExList);
                log.info("Finish update EmployeeTransformEx status code 201 to 200");
            }
            return body;
        } else {
            return new ResponseDTO("No transferred workers");
        }
    }
}
