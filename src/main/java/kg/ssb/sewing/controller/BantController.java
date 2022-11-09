package kg.ssb.sewing.controller;

import kg.ssb.sewing.dto.BantDTO;
import kg.ssb.sewing.dto.BantDTOTrue;
import kg.ssb.sewing.dto.UserDTO;
import kg.ssb.sewing.entity.User;
import kg.ssb.sewing.facade.UserFacade;
import kg.ssb.sewing.services.BantService;
import kg.ssb.sewing.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/api/bant")
@Slf4j
@RequiredArgsConstructor
@CrossOrigin
public class BantController {

    private final BantService bantService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<List<BantDTO>> getAllBant() {
        List<BantDTO> bantDTOList = bantService.getAll();
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }

    @GetMapping("/{masterUuid}")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<List<BantDTO>> getBantsByMasterUuid(@PathVariable String masterUuid) {
        List<BantDTO> bantDTOList = bantService.getBantsMasterUui(masterUuid);
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }

    @GetMapping("/save")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<String> saveAllBant() {
        return new ResponseEntity<>(bantService.getAllTo1c(), HttpStatus.OK);
    }

    @GetMapping("/isTrue")
    @PreAuthorize("hasAnyRole('MASTER','SEAMSTRESS')")
    public ResponseEntity<List<BantDTOTrue>> getAllBantList() {
        List<BantDTOTrue> bantDTOList = bantService.getAllListTrue();
        return new ResponseEntity<>(bantDTOList, HttpStatus.OK);
    }



}
