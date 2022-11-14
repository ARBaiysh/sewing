package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.EmployeeDTO;
import kg.ssb.sewing.dto.EmployeeUpdateWorkPlaceUuidDTO;
import kg.ssb.sewing.dto.LoginRequestDTO;
import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.services.EmployeeService;
import kg.ssb.sewing.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/employee")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER')")
@CrossOrigin
public class EmployeeController {

    private final EmployeeService employeeService;
    private final UserService userService;

    @GetMapping()
    public ResponseEntity<List<EmployeeDTO>> getAllEmployee() {
        List<EmployeeDTO> bantDTOList = employeeService.getAll();
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }

    @GetMapping("/masterUuid/{masterUuid}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByMasterUuid(@PathVariable String masterUuid) {
        List<EmployeeDTO> bantDTOList = employeeService.getEmployeesMasterUui(masterUuid);
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }

    @GetMapping("/workPlaceUuid/{workPlaceUuid}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByWorkPlaceUuid(@PathVariable String workPlaceUuid) {
        List<EmployeeDTO> employeeDTOS = employeeService.getEmployeesWorkPlaceUuid(workPlaceUuid);
        return new ResponseEntity<>(employeeDTOS, HttpStatus.OK);
    }


    @GetMapping("/save")
    public ResponseEntity<String> saveAllEmployee() {
        return new ResponseEntity<>(employeeService.getAllTo1c(), HttpStatus.OK);
    }


    @PostMapping("/update")
    public ResponseEntity<Boolean> updateEmployeeWorkPlaceUuid(@Valid @RequestBody EmployeeUpdateWorkPlaceUuidDTO employeeUpdateWorkPlaceUuidDTO, Principal principal) {
        UserDTO currentUser = userService.getCurrentUser(principal);
        return new ResponseEntity<>(employeeService.updateEmployeeWorkPlaceUuid(employeeUpdateWorkPlaceUuidDTO, currentUser), HttpStatus.OK);
    }

}
