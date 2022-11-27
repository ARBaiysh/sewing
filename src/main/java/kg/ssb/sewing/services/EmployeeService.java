package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.EmployeeDTO;
import kg.ssb.sewing.dto.EmployeeUpdateWorkPlaceUuidDTO;
import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.entity.Employee;
import kg.ssb.sewing.entity.EmployeeTransformEx;
import kg.ssb.sewing.repository.EmployeeRepository;
import kg.ssb.sewing.rest.Rest1cClientEmployee;
import kg.ssb.sewing.utils.ConvertUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Rest1cClientEmployee rest1CClientEmployee;
    private final EmployeeTransformExService employeeTransformExService;
    private final ModelMapper modelMapper;

    public void saveEmployeeDTO(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employeeList = employeeDTOList.stream().map(employeeDTO -> modelMapper.map(employeeDTO, Employee.class)).collect(Collectors.toList());
        employeeRepository.saveAll(employeeList);
        log.info("Save employee total {}", employeeList.size());
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll().stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public String getAllTo1c() {
        saveEmployeeDTO(Objects.requireNonNull(rest1CClientEmployee.getAllEmployees().getBody()));
        return "Ok";
    }


    @Scheduled(cron = "0 15 20 * * *")
    public void checkEmployeesFromTheBase1c() {
        log.info("Start check employees from the base1c");
        List<EmployeeDTO> employeeDTOS = Objects.requireNonNull(rest1CClientEmployee.getAllEmployees().getBody());
        employeeDTOS.forEach(employeeDTO -> {
            if (employeeRepository.existsByUuid(employeeDTO.getUuid())) {
                Employee employee = employeeRepository.findByUuid(employeeDTO.getUuid());
                if (!employeeDTO.equals(modelMapper.map(employee, EmployeeDTO.class))) {
                    employee.setInn(employeeDTO.getInn());
                    employee.setPersonalId(employeeDTO.getPersonalId());
                    employee.setDateOfBirth(employeeDTO.getDateOfBirth());
                    employee.setFullName(employeeDTO.getFullName());
                    employee.setResidence(employeeDTO.getResidence());
                    employee.setPlaceOfRegistration(employeeDTO.getPlaceOfRegistration());
                    employee.setPosition(employeeDTO.getPosition());
                    employee.setPositionUuid(employeeDTO.getPositionUuid());
                    employee.setWorkPlace(employeeDTO.getWorkPlace());
                    employee.setWorkPlaceUuid(employeeDTO.getWorkPlaceUuid());
                    employee.setHasWorkPlace(employeeDTO.isHasWorkPlace());
                    employee.setMaster(employeeDTO.getMaster());
                    employee.setMasterUuid(employeeDTO.getMasterUuid());
                    employeeRepository.save(employee);
                    log.info("Updated employee from base1c, employee uuid - {}", employee.getUuid());
                }
            } else {
                employeeRepository.save(modelMapper.map(employeeDTO, Employee.class));
                log.info("Add new employee uuid - {}", employeeDTO.getUuid());
            }
        });
        log.info("Finish check employees from the base1c");
    }

    public Employee findEmployeeByEmployeeUuid(String employeeUuid) {
        return employeeRepository.findByUuid(employeeUuid);
    }

    public List<EmployeeDTO> getEmployeesMasterUui(String masterUuid) {
        return employeeRepository.findAllByMasterUuid(masterUuid).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesWorkPlaceUuid(String workPlaceUuid) {
        LocalDateTime now = LocalDateTime.now();
        return employeeRepository.findAllByWorkPlaceUuidToday(workPlaceUuid, now.toLocalDate().atStartOfDay(), now.toLocalDate().atTime(LocalTime.MAX)).stream().map(emp -> ConvertUtils.toCamelCase(emp, EmployeeDTO.class)).collect(Collectors.toList());

    }

    public boolean updateEmployeeWorkPlaceUuid(EmployeeUpdateWorkPlaceUuidDTO employeeUpdateWorkPlaceUuidDTO, UserDTO userDTO) {
        Employee employee = employeeRepository.findByUuid(employeeUpdateWorkPlaceUuidDTO.getUuid());
        employee.setWorkPlace(employeeUpdateWorkPlaceUuidDTO.getWorkPlace());
        employee.setWorkPlaceUuid(employeeUpdateWorkPlaceUuidDTO.getWorkPlaceUuid());
        employee.setMaster(employeeUpdateWorkPlaceUuidDTO.getMaster());
        employee.setMasterUuid(employeeUpdateWorkPlaceUuidDTO.getMasterUuid());

        EmployeeTransformEx employeeTransformEx = new EmployeeTransformEx();
        employeeTransformEx.setEmployeeUuid(employee.getUuid());
        employeeTransformEx.setCreateDateTime(LocalDateTime.now());
        employeeTransformEx.setAuthorUuid(userDTO.getUuid());
        employeeTransformEx.setWorkPlaceUuid(employee.getWorkPlaceUuid());
        employeeTransformEx.setStatusTransferIn1C(201);
        employeeTransformExService.saveEmployeeTransformEx(employeeTransformEx);
        log.info("Save employeeTransformEx current user-{}", userDTO.getPersonalId());
        employeeRepository.save(employee);
        log.info("Update employee name-{}", employee.getFullName());
        return true;
    }
}
