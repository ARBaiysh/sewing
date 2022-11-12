package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.EmployeeDTO;
import kg.ssb.sewing.entity.Employee;
import kg.ssb.sewing.repository.EmployeeRepository;
import kg.ssb.sewing.rest.Rest1cClientEmployee;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeService {
    private final EmployeeRepository employeeRepository;
    private final Rest1cClientEmployee rest1CClientEmployee;
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

    public List<EmployeeDTO> getEmployeesMasterUui(String masterUuid) {
        return employeeRepository.findAllByMasterUuid(masterUuid).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }

    public List<EmployeeDTO> getEmployeesWorkPlaceUuid(String workPlaceUuid) {
        return employeeRepository.findAllByWorkPlaceUuid(workPlaceUuid).stream().map(employee -> modelMapper.map(employee, EmployeeDTO.class)).collect(Collectors.toList());
    }
}
