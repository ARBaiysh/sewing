package kg.ssb.sewing.services;


import kg.ssb.sewing.entity.EmployeeTransformEx;
import kg.ssb.sewing.repository.EmployeeTransformExRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeTransformExService {
    private final EmployeeTransformExRepository employeeTransformExRepository;

    public void save(EmployeeTransformEx employeeTransformEx) {
        employeeTransformExRepository.save(employeeTransformEx);
    }
}
