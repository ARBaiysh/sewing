package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.EmployeeDetailDTOIn;
import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.services.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/employeeDetail")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class EmployeeDetailController {
    private final EmployeeDetailService employeeDetailService;

    @GetMapping("/{employeeUuid}")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<List<EmployeeDetail>> getAllEmployeeDetail(@PathVariable String employeeUuid) {
        return new ResponseEntity<>(employeeDetailService.getAllEmployeeDetails(employeeUuid), HttpStatus.OK);
    }

    @GetMapping("/today/{employeeUuid}")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<List<EmployeeDetail>> getAllEmployeeDetailToDay(@PathVariable String employeeUuid) {
        return new ResponseEntity<>(employeeDetailService.getAllEmployeeDetailsData(employeeUuid), HttpStatus.OK);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<EmployeeDetail> getEmployeeDetail(@RequestBody EmployeeDetailDTOIn employeeDetailDTOIn) {
        EmployeeDetail employeeDetail = employeeDetailService.getEmployeeDetailByEmployeeUuid(employeeDetailDTOIn);
        return new ResponseEntity<>(employeeDetail, HttpStatus.OK);
    }
}
