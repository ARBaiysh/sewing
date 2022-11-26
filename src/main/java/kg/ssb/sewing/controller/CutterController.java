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
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER')")
@CrossOrigin
public class CutterController {

    private final Rest1cClientCutter rest1cClientCutter;


    @GetMapping("/{userUuid}")
    public ResponseEntity<Object> getCutterByStorehouseId(@PathVariable String userUuid) {
        return new ResponseEntity<>(rest1cClientCutter.getCutterByUserUuid(userUuid).getBody(), HttpStatus.OK);
    }
}
