package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.WorkplaceDTO;
import kg.ssb.sewing.entity.Workplace;
import kg.ssb.sewing.repository.WorkplaceRepository;
import kg.ssb.sewing.rest.Rest1cClientWorkplace;
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
public class WorkplaceService {
    private final Rest1cClientWorkplace rest1cClientWorkplace;
    private final WorkplaceRepository workplaceRepository;
    private final ModelMapper modelMapper;

    public List<WorkplaceDTO> getAllWorkplace() {
        return workplaceRepository.findAll().stream().map(workplace -> modelMapper.map(workplace, WorkplaceDTO.class)).collect(Collectors.toList());
    }

    public String saveWorkplace() {
        List<WorkplaceDTO> body = Objects.requireNonNull(rest1cClientWorkplace.getWorkplace().getBody());
        workplaceRepository.saveAll(body.stream().map(workplace -> modelMapper.map(workplace, Workplace.class)).collect(Collectors.toList()));
        return "ok total -" + body.size();
    }
}
