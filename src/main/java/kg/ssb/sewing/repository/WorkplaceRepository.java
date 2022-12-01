package kg.ssb.sewing.repository;

import kg.ssb.sewing.dto.WorkplaceDTO;
import kg.ssb.sewing.entity.Workplace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkplaceRepository extends JpaRepository<Workplace, Long> {

    List<Workplace> findAllByMasterUuid(String masterUuid);
}
