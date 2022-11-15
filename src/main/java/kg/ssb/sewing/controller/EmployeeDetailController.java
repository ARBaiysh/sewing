package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.EmployeeDetailDTO;
import kg.ssb.sewing.dto.ResponseDTO;
import kg.ssb.sewing.entity.EmployeeDetail;
import kg.ssb.sewing.services.EmployeeDetailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/employeeDetail")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER')")
@CrossOrigin
public class EmployeeDetailController {
    private final EmployeeDetailService employeeDetailService;

    @GetMapping("/{employeeUuid}")
    public ResponseEntity<List<EmployeeDetail>> getAllEmployeeDetail(@PathVariable String employeeUuid) {
        return new ResponseEntity<>(employeeDetailService.getAllEmployeeDetails(employeeUuid), HttpStatus.OK);
    }

    @GetMapping("/today/{employeeUuid}")
    public ResponseEntity<List<EmployeeDetail>> getAllEmployeeDetailToDay(@PathVariable String employeeUuid) {
        return new ResponseEntity<>(employeeDetailService.getAllEmployeeDetailsToDay(employeeUuid), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDetail> saveEmployeeDetail(@RequestBody EmployeeDetailDTO employeeDetailDTO, Principal principal) {
        EmployeeDetail employeeDetail = employeeDetailService.saveEmployeeDetail(employeeDetailDTO, principal);
        return new ResponseEntity<>(employeeDetail, HttpStatus.OK);
    }

    @GetMapping("/today/autoStop/")
    public ResponseEntity<?> autoStopAllEmployeeDetailToday() {
        employeeDetailService.autoStopEmployeeDetailAndEmployeeDetailEx();
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/today/sendTo1c/")
    public ResponseEntity<ResponseDTO> sendAllEmployeeDetailTodayTo1c() {
        return new ResponseEntity<>(employeeDetailService.sendEmployeeDetailToBase1c(), HttpStatus.OK);
    }

}
