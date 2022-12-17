package kg.ssb.sewing.controller;

import kg.ssb.sewing.rest.Rest1cClientSemiFinished;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/semi_finished")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_MASTER','ROLE_SEAMSTRESS','ROLE_MASTERS_LEADER','ROLE_HEAD_OF_CUTTING','ROLE_CONTROLLER','ROLE_PRINTER','ROLE_PACKER')")
@CrossOrigin
public class SemiFinishedController {

    private final Rest1cClientSemiFinished rest1cClientSemiFinished;


    @GetMapping()
    public ResponseEntity<Object> getStorehouse(@RequestParam String userUuid, @RequestParam String role) {
        log.info("Start getStorehouse");
        ResponseEntity<Object> storehouse = rest1cClientSemiFinished.getSemiFinished(userUuid, role);
        log.info("Finish getStorehouse response status-{}", storehouse.getStatusCode());
        return new ResponseEntity<>(storehouse.getBody(), HttpStatus.OK);
    }

    @PostMapping()
    public ResponseEntity<Object> postStorehouse(@RequestBody Object obj) {
        log.info("Start getStorehouse");
        ResponseEntity<Object> storehouse = rest1cClientSemiFinished.postSemiFinished(obj);
        log.info("Finish getStorehouse response status-{}", storehouse.getStatusCode());
        return new ResponseEntity<>(storehouse.getBody(), HttpStatus.OK);
    }
}
