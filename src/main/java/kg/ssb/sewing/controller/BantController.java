package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.BantDTO;
import kg.ssb.sewing.services.BantService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bant")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER')")
@CrossOrigin
public class BantController {

    private final BantService bantService;

    @GetMapping()
    public ResponseEntity<List<BantDTO>> getAllBant() {
        List<BantDTO> bantDTOList = bantService.getAll();
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }

    @GetMapping("/{masterUuid}")
    public ResponseEntity<List<BantDTO>> getBantsByMasterUuid(@PathVariable String masterUuid) {
        List<BantDTO> bantDTOList = bantService.getBantsMasterUui(masterUuid);
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }

    @GetMapping("/save")
    public ResponseEntity<String> saveAllBant() {
        return new ResponseEntity<>(bantService.getAllTo1c(), HttpStatus.OK);

    }
}
