package kg.ssb.sewing.services;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AutoSendTo1cService {
    private final EmployeeDetailService employeeDetailService;
    private final EmployeeTransformExService employeeTransformExService;

    @Scheduled(cron = "0 0 20 * * *")
    private void sentAllTo1c(){
        employeeDetailService.autoStopEmployeeDetailAndEmployeeDetailEx();
        employeeTransformExService.sendTo1cEmployeeTransformEx();
    }
}
