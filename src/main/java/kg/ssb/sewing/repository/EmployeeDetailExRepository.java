package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeDetailEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmployeeDetailExRepository extends JpaRepository<EmployeeDetailEx, Long> {

    EmployeeDetailEx findFirstByEmployeeUuidOrderByStartDateTimeDesc(String employeeUuid);

    List<EmployeeDetailEx> findAllByStartDateTimeBetween(LocalDateTime startDateTime, LocalDateTime stopDateTime);

}

