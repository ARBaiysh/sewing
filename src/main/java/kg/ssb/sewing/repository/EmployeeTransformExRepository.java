package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeTransformEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeTransformExRepository extends JpaRepository<EmployeeTransformEx, Long> {

}
