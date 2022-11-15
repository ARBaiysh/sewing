package kg.ssb.sewing.services;


import kg.ssb.sewing.dto.EmployeeDetailDTO;
import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.entity.Employee;
import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.entity.EmployeeDetailEx;
import kg.ssb.sewing.repository.EmployeeDetailExRepository;
import kg.ssb.sewing.repository.EmployeeDetailRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeDetailService {
    private final EmployeeDetailRepository employeeDetailRepository;
    private final EmployeeDetailExRepository employeeDetailExRepository;
    private final EmployeeService employeeService;
    private final UserService userService;
    private final ModelMapper modelMapper;

    @Value("${autoStop.uuid}")
    public String AUTO_STOP_UUID;
    @Value("${autoStop.name}")
    public String AUTO_STOP_NAME;
    @Value("${autoStop.defaultRating}")
    public double DEFAULT_RATING;
    @Value("${autoStop.defaultFine}")
    public double DEFAULT_FINE;
    @Value("${autoStop.time}")
    public int DEFAULT_TIME;


    public EmployeeDetail saveEmployeeDetail(EmployeeDetailDTO employeeDetailDTO, Principal principal) {
        if (!employeeDetailDTO.getAction().isEmpty()) {
            UserDTO userDTO = userService.currentUserUpdate(principal);
            Employee employee = employeeService.findEmployeeByEmployeeUuid(employeeDetailDTO.getEmployeeUuid());

            if (employeeDetailDTO.getAction().equals("start")) {
                EmployeeDetailEx employeeDetailEx = new EmployeeDetailEx();
                employeeDetailEx.setEmployeeUuid(employee.getUuid());
                employeeDetailEx.setAuthorUuidStart(userDTO.getUuid());
                employeeDetailEx.setPositionUuid(employee.getPositionUuid());
                employeeDetailEx.setStartDateTime(LocalDateTime.now());
                employeeDetailEx.setCauseStart(employeeDetailDTO.getCause());
                saveEmployeeDetailEx(employeeDetailEx);
                log.info("Create employeeDetailEx employee name - {}", employee.getFullName());
            } else if (employeeDetailDTO.getAction().equals("stop")) {
                EmployeeDetailEx employeeDetailEx = findLastEmployeeDatailByEmployeeUuid(employeeDetailDTO.getEmployeeUuid());
                employeeDetailEx.setAuthorUuidStop(userDTO.getUuid());
                LocalDateTime startDateTime = employeeDetailEx.getStartDateTime();
                LocalDateTime stopDataTime = LocalDateTime.now();
                employeeDetailEx.setStopDateTime(stopDataTime);
                LocalTime timeDifference = LocalTime.ofSecondOfDay(Duration.between(startDateTime, stopDataTime).getSeconds());
                employeeDetailEx.setWorkedHours(timeDifference);
                employeeDetailEx.setCauseStop(employeeDetailDTO.getCause());
                employeeDetailEx.setRating(employeeDetailDTO.getRating());
                employeeDetailEx.setFine(employeeDetailDTO.getFine());
                saveEmployeeDetailEx(employeeDetailEx);
                log.info("Update employeeDetailEx employee name - {} проработал {} ", employee.getFullName(), timeDifference);
            }
            EmployeeDetail employeeDetail = modelMapper.map(employeeDetailDTO, EmployeeDetail.class);
            employeeDetail.setDateTime(LocalDateTime.now());
            return employeeDetailRepository.save(employeeDetail);
        } else {
            return employeeDetailRepository.findFirstByOrderByIdDesc();
        }
    }

    public List<EmployeeDetail> getAllEmployeeDetailsToDay(String employeeUuid) {
        LocalDateTime now = LocalDateTime.now();
        return employeeDetailRepository.findAllByEmployeeUuidAndDateTimeBetween(employeeUuid, now.toLocalDate().atStartOfDay(), now.toLocalDate().atTime(LocalTime.MAX));
    }

    public List<EmployeeDetail> getAllEmployeeDetails(String employeeUuid) {
        return employeeDetailRepository.findAllByEmployeeUuidOrderByIdDesc(employeeUuid);
    }

    public void saveEmployeeDetailEx(EmployeeDetailEx employeeDetailEx) {
        employeeDetailExRepository.save(employeeDetailEx);
    }

    public EmployeeDetailEx findLastEmployeeDatailByEmployeeUuid(String employeeUuid) {
        return employeeDetailExRepository.findFirstByEmployeeUuidOrderByStartDateTimeDesc(employeeUuid);
    }

    public void autoStopEmployeeDetailAndEmployeeDetailEx() {
        log.info("Auto start finish");
        autoStopEmployeeDetailEx(findEmployeeDetailExToDay());
        log.info("Auto stop finish");
    }

    public List<EmployeeDetailEx> findEmployeeDetailExToDay() {
        LocalDateTime now = LocalDateTime.now();
        return employeeDetailExRepository.findAllByStartDateTimeBetween(now.toLocalDate().atStartOfDay(), now.toLocalDate().atTime(LocalTime.MAX));
    }


    public List<EmployeeDetailEx> autoStopEmployeeDetailEx(List<EmployeeDetailEx> employeeDetailExes) {
        employeeDetailExes.forEach(employeeDetailEx -> {
            if (employeeDetailEx.getCauseStop() == null) {
                employeeDetailEx.setAuthorUuidStop(AUTO_STOP_UUID);
                LocalDateTime startDateTime = employeeDetailEx.getStartDateTime();
                LocalDateTime now = LocalDateTime.now();
                LocalDateTime stopDataTime = LocalDateTime.of(now.getYear(), now.getMonth(), now.getDayOfMonth(), DEFAULT_TIME, 0, 0, 0);

                employeeDetailEx.setStopDateTime(stopDataTime);
                LocalTime timeDifference = LocalTime.ofSecondOfDay(Duration.between(startDateTime, stopDataTime).getSeconds());
                employeeDetailEx.setWorkedHours(timeDifference);
                employeeDetailEx.setCauseStop(AUTO_STOP_NAME);
                employeeDetailEx.setRating(DEFAULT_RATING);
                employeeDetailEx.setFine(DEFAULT_FINE);
                employeeDetailExRepository.save(employeeDetailEx);

                EmployeeDetail employeeDetail = new EmployeeDetail();
                employeeDetail.setEmployeeUuid(employeeDetailEx.getEmployeeUuid());
                employeeDetail.setMasterUuid(AUTO_STOP_UUID);
                employeeDetail.setDateTime(stopDataTime);
                employeeDetail.setAction("stop");
                employeeDetail.setCause(AUTO_STOP_NAME);
                employeeDetail.setRating(DEFAULT_RATING);
                employeeDetail.setFine(DEFAULT_FINE);
                employeeDetailRepository.save(employeeDetail);
                log.info("AutoStop employeeDetailEx employee id - {} : проработал {} ", employeeDetailEx.getEmployeeUuid(), timeDifference);
            }
        });
        return employeeDetailExes;
    }
}
