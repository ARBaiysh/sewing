package kg.ssb.sewing.services;

import feign.FeignException;
import kg.ssb.sewing.rest.Rest1cClientLeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfficeService {
    private final Rest1cClientLeader rest1cClientLeader;

    @Scheduled(fixedRate = 15000)
    private void check1sBases() {
        try {
            rest1cClientLeader.get();
        } catch (FeignException ex) {
            log.error("Error massage {}", ex.getMessage());
        }
    }
}
