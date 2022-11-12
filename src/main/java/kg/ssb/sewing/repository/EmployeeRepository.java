package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByMasterUuid(String masterUuid);
    List<Employee> findAllByWorkPlaceUuid(String workPlaceUui);

}
