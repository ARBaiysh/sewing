package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {


    EmployeeDetail findFirstByOrderByIdDesc();

    List<EmployeeDetail> findAllByEmployeeUuidOrderByIdDesc(String employeeUuid);

    List<EmployeeDetail> findAllByEmployeeUuidAndDateTimeBetween(String employeeUuid, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);
}
