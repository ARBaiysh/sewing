package kg.ssb.sewing.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoSendTo1cService {
    private final EmployeeService employeeService;
    private final LeaderService leaderService;
    private final EmployeeTransformExService employeeTransformExService;

    @Scheduled(cron = "0 * * * *")
    private void sentAllTo1c() {
        employeeTransformExService.sendTo1cEmployeeTransformEx();
        employeeService.checkEmployeesFromTheBase1c();
        leaderService.checkLeadersFromTheBase1c();
    }
}
