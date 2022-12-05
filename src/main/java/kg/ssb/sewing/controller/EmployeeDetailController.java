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
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER','ROLE_HEAD_OF_CUTTING','ROLE_CONTROLLER','ROLE_PRINTER')")
@CrossOrigin
public class EmployeeDetailController {
    private final EmployeeDetailService employeeDetailService;

    @GetMapping("/{employeeUuid}")
    public ResponseEntity<List<EmployeeDetail>> getAllEmployeeDetail(@PathVariable String employeeUuid) {
        log.info("Start getAllEmployeeDetail employeeUuid = {}", employeeUuid);
        List<EmployeeDetail> allEmployeeDetails = employeeDetailService.getAllEmployeeDetails(employeeUuid);
        log.info("Finish getAllEmployeeDetail employeeUuid = {}", employeeUuid);
        return new ResponseEntity<>(allEmployeeDetails, HttpStatus.OK);
    }

    @GetMapping("/today/{employeeUuid}")
    public ResponseEntity<List<EmployeeDetail>> getAllEmployeeDetailToDay(@PathVariable String employeeUuid) {
        log.info("Start getAllEmployeeDetailToDay employeeUuid = {}", employeeUuid);
        List<EmployeeDetail> allEmployeeDetailsToDay = employeeDetailService.getAllEmployeeDetailsToDay(employeeUuid);
        log.info("Finish getAllEmployeeDetailToDay employeeUuid = {}", employeeUuid);
        return new ResponseEntity<>(allEmployeeDetailsToDay, HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<EmployeeDetail> saveEmployeeDetail(@RequestBody EmployeeDetailDTO employeeDetailDTO, Principal principal) {
        log.info("Start saveEmployeeDetail employeeDetailDTO = {}", employeeDetailDTO.getEmployeeUuid());
        EmployeeDetail employeeDetail = employeeDetailService.saveEmployeeDetail(employeeDetailDTO, principal);
        log.info("Finish saveEmployeeDetail employeeDetailDTO = {}", employeeDetailDTO.getEmployeeUuid());
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
