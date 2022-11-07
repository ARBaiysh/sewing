package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.EmployeeDetailDTOIn;
import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.entity.EmployeeWorkingTime;
import kg.ssb.sewing.facade.EmployeeDetailWorkingTimeFacade;
import kg.ssb.sewing.services.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/employeeDetail")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeDetailController {
    private final EmployeeDetailService employeeDetailService;

    @GetMapping("/{employeeUuid}")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<EmployeeDetail> getAllEmployeeDetail(@PathVariable String employeeUuid) {
        EmployeeDetail employeeDetail = employeeDetailService.getEmployeeDetailByEmployeeUuid(employeeUuid);
        return new ResponseEntity<>(employeeDetail, HttpStatus.OK);
    }

    @PostMapping("/save")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<EmployeeDetail> saveEmployeeWorkingTime(@RequestBody EmployeeDetailDTOIn employeeDetailDTOIn) {
        EmployeeWorkingTime employeeWorkingTime = EmployeeDetailWorkingTimeFacade.employeeDetailDTOInToEmployeeWorkingTime(employeeDetailDTOIn);
        EmployeeDetail employeeDetail = employeeDetailService.saveEmployeeDetailWorkingTime(employeeWorkingTime);
        return new ResponseEntity<>(employeeDetail, HttpStatus.OK);
    }

}
