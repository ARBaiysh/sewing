package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.EmployeeDTO;
import kg.ssb.sewing.dto.EmployeeUpdateWorkPlaceUuidDTO;
import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.entity.Employee;
import kg.ssb.sewing.entity.EmployeeTransformEx;
import kg.ssb.sewing.repository.EmployeeRepository;
import kg.ssb.sewing.rest.Rest1cClientEmployee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    public void saveEmployee(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employeeList = employeeDTOList.stream().map(employeeDTO -> modelMapper.map(employeeDTO, Employee.class)).collect(Collectors.toList());
        employeeRepository.saveAll(employeeList);
        log.info("save employee total {}", employeeList.size());
    }

    public List<EmployeeDTO> getAll() {
        return employeeRepository.findAll().stream()
                .map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public String getAllTo1c() {
        saveEmployee(Objects.requireNonNull(rest1CClientEmployee.getAllEmployees().getBody()));
        return "Ok";
    }

    public Employee findEmployeeByEmployeeUuid(String employeeUuid){
        return  employeeRepository.findByUuid(employeeUuid);
    }

    public List<EmployeeDTO> getEmployeesMasterUui(String masterUuid) {
        return employeeRepository.findAllByMasterUuid(masterUuid).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesWorkPlaceUuid(String workPlaceUuid) {
        return employeeRepository.findAllByWorkPlaceUuid(workPlaceUuid).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
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
