package kg.ssb.sewing.services;

import kg.ssb.sewing.dto.BantDTO;
import kg.ssb.sewing.dto.BantDTOTrue;
import kg.ssb.sewing.entity.Bant;
import kg.ssb.sewing.facade.BantFacade;
import kg.ssb.sewing.repository.BantRepository;
import kg.ssb.sewing.rest.Rest1cClientBant;
import kg.ssb.sewing.rest.config.Rest1cConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class BantService {
    private final BantRepository bantRepository;
    private final Rest1cClientBant rest1cClientBant;

    public void saveBant(List<BantDTO> bantDTOList) {
        List<Bant> bantList = bantDTOList.stream().map(BantFacade::BantDTOToBant).collect(Collectors.toList());
        bantRepository.saveAll(bantList);
        log.info("save bant total {}", bantList.size());
    }

    public List<BantDTO> getAll() {
        return bantRepository.findAll().stream()
                .map(BantFacade::BantToBantDTO).collect(Collectors.toList());
    }

    public String getAllTo1c() {
        saveBant(Objects.requireNonNull(rest1cClientBant.getAllBants().getBody()));
        return "Ok";
    }

    public List<BantDTO> getBantsMasterUui(String masterUuid) {
        return bantRepository.findAllByMasterUuid(masterUuid).stream().map(BantFacade::BantToBantDTO).collect(Collectors.toList());
    }

    public List<BantDTOTrue> getAllListTrue() {
        List<BantDTOTrue> bantDTOTrues = new ArrayList<>();
        bantRepository.findAllByHasWorkPlace(true)
                .forEach(bant -> bantDTOTrues.add(new BantDTOTrue(bant.getWorkPlace(), bant.getWorkPlaceUuid())));
        return bantDTOTrues;
    }
}
