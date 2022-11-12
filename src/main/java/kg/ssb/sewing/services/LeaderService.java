package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.LeaderDto;
import kg.ssb.sewing.entity.Leader;
import kg.ssb.sewing.repository.LeaderRepository;
import kg.ssb.sewing.rest.Rest1cClientLeader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.modelmapper.ModelMapper;
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

    public String saveLeader() {
        List<LeaderDto> leaderDtos = Objects.requireNonNull(rest1cClientLeader.get().getBody());
        leaderRepository.saveAll(leaderDtos.stream().map(leaderDto -> modelMapper.map(leaderDto, Leader.class)).collect(Collectors.toList()));
        return "save leader total - "+ leaderDtos.size();
    }


    public List<LeaderDto> getLeaders() {
        return leaderRepository.findAll().stream().map(leader -> modelMapper.map(leader, LeaderDto.class)).collect(Collectors.toList());
    }
}
