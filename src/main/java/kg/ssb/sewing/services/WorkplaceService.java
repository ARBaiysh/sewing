package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.WorkplaceDTO;
import kg.ssb.sewing.entity.Workplace;
import kg.ssb.sewing.facade.WorkplaceFacade;
import kg.ssb.sewing.repository.WorkplaceRepository;
import kg.ssb.sewing.rest.Rest1cClientWorkplace;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WorkplaceService {
    private final Rest1cClientWorkplace rest1cClientWorkplace;
    private final WorkplaceRepository workplaceRepository;

    public List<WorkplaceDTO> getAllWorkplace() {
        return workplaceRepository.findAll().stream().map(WorkplaceFacade::WorkplaceToWorkplaceDTO).collect(Collectors.toList());
    }

    public String saveWorkplace() {
        List<WorkplaceDTO> body = Objects.requireNonNull(rest1cClientWorkplace.getWorkplace().getBody());
        workplaceRepository.saveAll(body.stream().map(WorkplaceFacade::WorkplaceDTOToWorkplace).collect(Collectors.toList()));
        return "ok total -" + body.size();
    }
}
