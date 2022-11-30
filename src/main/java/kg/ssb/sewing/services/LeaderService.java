package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.LeaderDto;
import kg.ssb.sewing.entity.Leader;
import kg.ssb.sewing.repository.LeaderRepository;
import kg.ssb.sewing.rest.Rest1cClientLeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class LeaderService {
    private final LeaderRepository leaderRepository;
    private final Rest1cClientLeader rest1cClientLeader;
    private final ModelMapper modelMapper;

    @Scheduled(cron = "0 15 20 * * *")
    public String checkLeadersFromTheBase1c() {
        log.info("Start check leaders from the base1c");
        List<LeaderDto> leaderDtos = Objects.requireNonNull(rest1cClientLeader.get().getBody());
        leaderDtos.forEach(leaderDto -> {
            if (leaderRepository.existsByWorkPlaceUuid(leaderDto.getWorkPlaceUuid())) {
                Leader leader = leaderRepository.findByWorkPlaceUuid(leaderDto.getWorkPlaceUuid());
                if(!leaderDto.equals(modelMapper.map(leader,LeaderDto.class))){
                    leader.setLeaderUuid(leaderDto.getLeaderUuid());
                    leader.setLeader(leaderDto.getLeader());
                    leader.setInn(leaderDto.getInn());
                    leader.setPersonalId(leaderDto.getPersonalId());
                    leader.setDateOfBirth(leaderDto.getDateOfBirth());
                    leader.setFullName(leaderDto.getFullName());
                    leader.setResidence(leaderDto.getResidence());
                    leader.setPlaceOfRegistration(leaderDto.getPlaceOfRegistration());
                    leader.setUuid(leaderDto.getUuid());
                    leader.setWorkPlace(leaderDto.getWorkPlace());
                    leaderRepository.save(leader);
                    log.info("Updated leader from base1c, leader uuid - {}", leader.getLeaderUuid());

                }
            }else {
                Leader leader = modelMapper.map(leaderDto, Leader.class);
                Leader save = leaderRepository.save(leader);
                log.info("Add new leader id - {} uuid - {} tabel {}", save.getId(), save.getUuid(), save.getPersonalId());
            }
        });

        List<Leader> leaderList = leaderRepository.findAll();
        leaderList.forEach(leader -> {
            LeaderDto leaderDto = modelMapper.map(leader, LeaderDto.class);
            if (!leaderDtos.contains(leaderDto)){
                leaderRepository.delete(leader);
                log.info("Remove an leader from the database, leader uuid - {}", leader.getLeaderUuid());
            }
        });
        log.info("Finish check leaders from the base1c");
        return "ok";
    }


    public List<LeaderDto> getLeaders() {
        return leaderRepository.findAll().stream().map(leader -> modelMapper.map(leader, LeaderDto.class)).collect(Collectors.toList());
    }

    public List<LeaderDto> getLeadersByLeaderUuid(String leaderUuid) {
        List<Leader> allByLeaderUuid = leaderRepository.findAllByLeaderUuid(leaderUuid);
        return allByLeaderUuid.stream().map(leader -> modelMapper.map(leader, LeaderDto.class)).collect(Collectors.toList());
    }
}
