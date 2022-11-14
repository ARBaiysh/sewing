package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.EmployeeDetailExport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeDetailExportRepository extends JpaRepository<EmployeeDetailExport, Long> {

}
