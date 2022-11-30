package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {
    List<Leader> findAllByLeaderUuid(String leaderUuid);

    boolean existsByWorkPlaceUuid(String workPlaceUuid);

    Leader findByWorkPlaceUuid(String workPlaceUuid);

    @Query(nativeQuery = true, value = "SELECT 1")
    int getOne();
}
