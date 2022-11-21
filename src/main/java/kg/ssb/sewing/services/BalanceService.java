package kg.ssb.sewing.services;

import kg.ssb.sewing.rest.Rest1cClientBalance;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BalanceService {
    private final Rest1cClientBalance rest1cClientBalance;

    public Object getBalanceByStorehouseId(String storehouseId) {
        ResponseEntity<Object> balanceByStorehouseId = rest1cClientBalance.getBalanceByStorehouseId(storehouseId);
        log.info("Get balance by storehouseId - {}, response code-{}", storehouseId, balanceByStorehouseId.getStatusCode());
        return balanceByStorehouseId.getBody();
    }

}
