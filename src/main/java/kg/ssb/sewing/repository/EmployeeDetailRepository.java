package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, Long> {

    boolean existsEmployeeDetailByEmployeeUuid(String employeeUuid);

    EmployeeDetail findFirstByOrderByIdDesc();

    List<EmployeeDetail> findAllByEmployeeUuidOrderByIdDesc(String employeeUuid);
}
