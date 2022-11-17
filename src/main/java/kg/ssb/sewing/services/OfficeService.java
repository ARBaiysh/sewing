package kg.ssb.sewing.services;

import kg.ssb.sewing.repository.LeaderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OfficeService {
    private final LeaderRepository leaderRepository;

    @Scheduled(fixedRate = 5000)
    public void checkMysql() {
        leaderRepository.getOne();
    }
}
