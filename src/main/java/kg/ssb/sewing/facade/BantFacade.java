package kg.ssb.sewing.facade;

import kg.ssb.sewing.dto.BantDTO;
import kg.ssb.sewing.entity.Bant;

public class BantFacade {
    public static BantDTO BantToBantDTO(Bant bant) {
        return new BantDTO(
                bant.getInn(),
                bant.getPersonalId(),
                bant.getDateOfBirth(),
                bant.getFullName(),
                bant.getResidence(),
                bant.getPlaceOfRegistration(),
                bant.getUuid(),
                bant.getPosition(),
                bant.getPositionUuid(),
                bant.getDivision(),
                bant.getDivisionUuid(),
                bant.getWorkPlace(),
                bant.getWorkPlaceUuid(),
                bant.getHasWorkPlace(),
                bant.getMaster(),
                bant.getMasterUuid());
    }

    public static Bant BantDTOToBant(BantDTO bantDTO) {
        return new Bant(
                bantDTO.getInn(),
                bantDTO.getPersonalId(),
                bantDTO.getDateOfBirth(),
                bantDTO.getFullName(),
                bantDTO.getResidence(),
                bantDTO.getPlaceOfRegistration(),
                bantDTO.getUuid(),
                bantDTO.getPosition(),
                bantDTO.getPositionUuid(),
                bantDTO.getDivision(),
                bantDTO.getDivisionUuid(),
                bantDTO.getWorkPlace(),
                bantDTO.getWorkPlaceUuid(),
                bantDTO.getHasWorkPlace(),
                bantDTO.getMaster(),
                bantDTO.getMasterUuid());
    }
}
