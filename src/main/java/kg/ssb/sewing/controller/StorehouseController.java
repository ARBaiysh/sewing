package kg.ssb.sewing.controller;

import kg.ssb.sewing.rest.Rest1cClientStorehouse;
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
@RequestMapping("/api/storehouse")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_MASTER','ROLE_SEAMSTRESS','ROLE_MASTERS_LEADER','ROLE_HEAD_OF_CUTTING','ROLE_CONTROLLER','ROLE_PRINTER')")
@CrossOrigin
public class StorehouseController {

    private final Rest1cClientStorehouse rest1cClientStorehouse;


    @GetMapping()
    public ResponseEntity<Object> getStorehouse() {
        log.info("Start getStorehouse");
        ResponseEntity<Object> storehouse = rest1cClientStorehouse.getStorehouse();
        log.info("Finish getStorehouse response status-{}", storehouse.getStatusCode());
        return new ResponseEntity<>(storehouse.getBody(), HttpStatus.OK);
    }
}
