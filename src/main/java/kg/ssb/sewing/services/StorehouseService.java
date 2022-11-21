package kg.ssb.sewing.services;

import kg.ssb.sewing.rest.Rest1cClientStorehouse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class StorehouseService {
    private final Rest1cClientStorehouse rest1cClientStorehouse;

    public Object getStorehouse() {
        ResponseEntity<Object> storehouse = rest1cClientStorehouse.getStorehouse();
        log.info("Get list storehouse, response code-{}", storehouse.getStatusCode());
        return storehouse.getBody();
    }

}
