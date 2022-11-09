package kg.ssb.sewing.facade;

import kg.ssb.sewing.dto.WorkplaceDTO;
import kg.ssb.sewing.entity.Workplace;

public class WorkplaceFacade {
    public static WorkplaceDTO WorkplaceToWorkplaceDTO(Workplace workplace) {
        return new WorkplaceDTO(
                workplace.getWorkPlace(),
                workplace.getWorkPlaceUuid(),
                workplace.getMaster(),
                workplace.getMasterUuid());
    }

    public static Workplace WorkplaceDTOToWorkplace(WorkplaceDTO workplaceDTO) {
        return new Workplace(
                workplaceDTO.getWorkPlace(),
                workplaceDTO.getWorkPlaceUuid(),
                workplaceDTO.getMaster(),
                workplaceDTO.getMasterUuid());
    }
}
