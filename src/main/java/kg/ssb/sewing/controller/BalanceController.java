package kg.ssb.sewing.controller;

import kg.ssb.sewing.services.BalanceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/balance")
@Slf4j
@RequiredArgsConstructor
@PreAuthorize(value = "hasAnyRole('MASTER','SEAMSTRESS','MASTERS_LEADER')")
@CrossOrigin
public class BalanceController {

    private final BalanceService balanceService;


    @GetMapping("/{storehouseId}")
    public ResponseEntity<Object> getBalanceByStorehouseId(@PathVariable String storehouseId) {
        return new ResponseEntity<>(balanceService.getBalanceByStorehouseId(storehouseId), HttpStatus.OK);
    }
}
