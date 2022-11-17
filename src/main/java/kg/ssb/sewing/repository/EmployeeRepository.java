package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    List<Employee> findAllByMasterUuid(String masterUuid);

    Employee findByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT emp.date_of_birth, emp.division, emp.division_uuid, emp.full_name," +
            " emp.has_work_place, emp.inn, emp.master, emp.master_uuid, emp.personal_id, emp.place_of_registration, " +
            "emp.position, emp.position_uuid, emp.residence,emp.uuid,emp.work_place, emp.work_place_uuid," +
            "(select action from employee_detail where employee_uuid=emp.uuid and date_time BETWEEN  :dateTimeStart AND :dateTimeEnd order by date_time desc limit 1) as action " +
            "FROM employee as emp " +
            "WHERE emp.work_place_uuid= :employeeUuid")
    List<Map<String, Object>> findAllByWorkPlaceUuidToday(String employeeUuid, LocalDateTime dateTimeStart, LocalDateTime dateTimeEnd);
}
