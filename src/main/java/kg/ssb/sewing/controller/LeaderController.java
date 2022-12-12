package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.LeaderDto;
import kg.ssb.sewing.services.LeaderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leader")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('ROLE_MASTER','ROLE_SEAMSTRESS','ROLE_MASTERS_LEADER','ROLE_HEAD_OF_CUTTING','ROLE_CONTROLLER','ROLE_PRINTER')")
@CrossOrigin
public class LeaderController {
    private final LeaderService leaderService;

    @GetMapping()
    public ResponseEntity<List<LeaderDto>> getLeaders() {
        List<LeaderDto> leaderDtos = leaderService.getLeaders();
        return new ResponseEntity<>(leaderDtos, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<LeaderDto>> getLeaderById(@PathVariable String id) {
        List<LeaderDto> leaderDtos = leaderService.getLeadersByLeaderUuid(id);
        return new ResponseEntity<>(leaderDtos, HttpStatus.OK);
    }
}
