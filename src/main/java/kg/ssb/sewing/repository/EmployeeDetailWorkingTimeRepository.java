package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeWorkingTime;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDetailWorkingTimeRepository extends JpaRepository<EmployeeWorkingTime, String> {

    List<EmployeeWorkingTime> getAllByEmployeeUuidOrderByDateTimeDesc(String employeeUuid);
}
