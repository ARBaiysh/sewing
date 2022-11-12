package kg.ssb.sewing.repository;

import kg.ssb.sewing.entity.Leader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LeaderRepository extends JpaRepository<Leader, Long> {
    List<Leader> findAllByLeaderUuid(String leaderUuid);
}
