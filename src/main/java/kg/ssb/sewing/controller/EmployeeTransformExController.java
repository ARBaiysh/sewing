package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.ResponseDTO;
import kg.ssb.sewing.services.EmployeeTransformExService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transportEx")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER')")
@CrossOrigin
public class EmployeeTransformExController {

    private final EmployeeTransformExService employeeTransformExService;

    @GetMapping("/today/sendTo1c/")
    public ResponseEntity<ResponseDTO> sendAllEmployeeTransformTodayTo1c() {
        return new ResponseEntity<>(employeeTransformExService.sendTo1cEmployeeTransformEx(), HttpStatus.OK);
    }

}
