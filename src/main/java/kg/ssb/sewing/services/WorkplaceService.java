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

    public void checkWorkplaceFromTheBase1c() {
        log.info("Start check workplace from the base1c");
        List<WorkplaceDTO> workplaceDTOS = Objects.requireNonNull(rest1cClientWorkplace.getWorkplace().getBody());
        workplaceDTOS.forEach(workplaceDTO -> {
            if (workplaceRepository.existsByWorkPlaceUuid(workplaceDTO.getWorkPlaceUuid())) {
                Workplace workplace = workplaceRepository.findByWorkPlaceUuid(workplaceDTO.getWorkPlaceUuid());
                if (!workplaceDTO.equals(modelMapper.map(workplace, WorkplaceDTO.class))) {
                    workplace.setWorkPlace(workplaceDTO.getWorkPlace());
                    workplace.setMaster(workplaceDTO.getMaster());
                    workplace.setMasterUuid(workplaceDTO.getMasterUuid());
                    workplaceRepository.save(workplace);
                    log.info("Updated workplace from base1c, workplace uuid - {}", workplace.getWorkPlaceUuid());
                }
            } else {
                Workplace workplace = modelMapper.map(workplaceDTO, Workplace.class);
                Workplace newWorkplace = workplaceRepository.save(workplace);
                log.info("Add new workplace id - {} uuid - {}", newWorkplace.getId(), newWorkplace.getWorkPlaceUuid());
            }
        });
        List<Workplace> workplaceList = workplaceRepository.findAll();
        workplaceList.forEach(workplace -> {
            WorkplaceDTO workplaceDTO = modelMapper.map(workplace, WorkplaceDTO.class);
            if (!workplaceDTOS.contains(workplaceDTO)) {
                workplaceRepository.delete(workplace);
                log.info("Remove an workplace from the database, workplace uuid - {}", workplace.getMasterUuid());
            }
        });
        log.info("Finish check workplace from the base1c");
    }

    public List<WorkplaceDTO> findAllWorkplaceByMasterUuid(String masterUuid) {
        return workplaceRepository.findAllByMasterUuid(masterUuid).stream().map(workplace -> modelMapper.map(workplace, WorkplaceDTO.class)).collect(Collectors.toList());
    }
}
