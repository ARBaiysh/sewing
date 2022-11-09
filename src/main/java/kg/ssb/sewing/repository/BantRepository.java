package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.Bant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BantRepository extends JpaRepository<Bant, Long> {
    List<Bant> findAllByMasterUuid(String masterUuid);

}
