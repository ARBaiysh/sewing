package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.WorkplaceDTO;
import kg.ssb.sewing.services.WorkplaceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/workplace")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class WorkplaceController {

    private final WorkplaceService workplaceService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<List<WorkplaceDTO>> getAllWorkplace() {
        return new ResponseEntity<>(workplaceService.getAllWorkplace(), HttpStatus.OK);
    }

    @GetMapping("/save")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<String> saveWorkplace() {
        return new ResponseEntity<>(workplaceService.saveWorkplace(), HttpStatus.OK);
    }
}
