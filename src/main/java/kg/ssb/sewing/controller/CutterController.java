package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.CutterDTO;
import kg.ssb.sewing.rest.Rest1cClientCutter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/cutter")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER','ROLE_HEAD_OF_CUTTING','ROLE_CONTROLLER','ROLE_PRINTER')")
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

    @PostMapping()
    public ResponseEntity<Object> setQRCodeTo1c(@RequestBody CutterDTO cutterDTO, Principal principal) {
        log.info("Start setQRCodeTo1c = {} user {}", cutterDTO.getQrCode(), principal.getName());
        ResponseEntity<Object> objectResponseEntity = rest1cClientCutter.setORCodeTo1c(cutterDTO);
        log.info("Finish setQRCodeTo1c response code -{}", objectResponseEntity.getStatusCode());
        return new ResponseEntity<>(objectResponseEntity.getBody(), HttpStatus.OK);
    }

    @PostMapping("/cut")
    public ResponseEntity<Object> setCutTo1c(@RequestBody Object object, Principal principal) {
        log.info("Start setCutTo1c user {}", principal.getName());
        ResponseEntity<Object> objectResponseEntity = rest1cClientCutter.setCutTo1c(object);
        log.info("Finish setCutTo1c response code -{}", objectResponseEntity.getStatusCode());
        return new ResponseEntity<>(objectResponseEntity.getBody(), HttpStatus.OK);
    }
}
