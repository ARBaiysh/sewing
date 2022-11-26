package kg.ssb.sewing.controller;

import kg.ssb.sewing.rest.Rest1cClientCutter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cutter")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER','ROLE_HEAD_OF_CUTTING')")
@CrossOrigin
public class CutterController {

    private final Rest1cClientCutter rest1cClientCutter;


    @GetMapping("/{userUuid}")
    public ResponseEntity<Object> getCutterByUserUuid(@PathVariable String userUuid) {
        log.info("Start getCutterByUserUuid-{}", userUuid);
        ResponseEntity<Object> cutterByUserUuid = rest1cClientCutter.getCutterByUserUuid(userUuid);
        log.info("Finish getCutterByUserUuid response status-{}", cutterByUserUuid.getStatusCode());
        return new ResponseEntity<>(cutterByUserUuid.getBody(), HttpStatus.OK);
    }
}
