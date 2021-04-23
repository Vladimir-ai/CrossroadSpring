package app.mapper;

import app.domain.DTO.AutomobileDTO;
import app.domain.entity.Automobile;
import org.mapstruct.Mapper;

public interface AutomobileMapper {

    Automobile automobileDTOToAutomobile(AutomobileDTO dto);

    AutomobileDTO autoToAutoDTO(Automobile ent);
}
