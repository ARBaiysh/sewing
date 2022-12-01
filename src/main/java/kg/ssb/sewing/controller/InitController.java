package kg.ssb.sewing.controller;

import kg.ssb.sewing.services.AutoSendTo1cService;
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
@RequestMapping("/api/init")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER','ROLE_HEAD_OF_CUTTING')")
@CrossOrigin
public class InitController {
    private final AutoSendTo1cService autoSendTo1cService;

    @GetMapping()
    public ResponseEntity<String> checkModelsFromTheBase1c() {
        autoSendTo1cService.sentAllTo1c();
        return new ResponseEntity<>("Ok", HttpStatus.OK);
    }
}
