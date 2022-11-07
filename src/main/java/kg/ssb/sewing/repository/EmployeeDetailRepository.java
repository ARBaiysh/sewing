package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailRepository extends JpaRepository<EmployeeDetail, String> {

    boolean existsEmployeeDetailByEmployeeUuid(String employeeUuid);

}
