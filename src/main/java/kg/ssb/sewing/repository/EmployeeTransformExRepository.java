package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeTransformEx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeTransformExRepository extends JpaRepository<EmployeeTransformEx, Long> {

    Optional<List<EmployeeTransformEx>> findAllByCreateDateTimeBetween(LocalDateTime startDateTime, LocalDateTime stopDateTime);

}
