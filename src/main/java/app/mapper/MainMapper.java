package app.mapper;

import app.domain.DTO.AutomobileDTO;
import app.domain.DTO.LineDTO;
import app.domain.DTO.RoadBlockDTO;
import app.domain.DTO.TrafficLightDTO;
import app.domain.entity.Automobile;
import app.domain.entity.Line;
import app.domain.entity.RoadBlock;
import app.domain.entity.TrafficLight;
import java.util.List;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MainMapper {

    Automobile autoDtoToAuto(AutomobileDTO dto);

    AutomobileDTO autoToAutoDTO(Automobile ent);

    RoadBlock blockDtoToBlock(RoadBlockDTO dto);

    RoadBlockDTO blockToBlockDTO(RoadBlock ent);

    Line lineDtoToLine(LineDTO dto);

    LineDTO lineToLineDTO(Line ent);

    TrafficLight trafficLightDtoTotrafficLight(TrafficLightDTO dto);

    TrafficLightDTO trafficLightTotrafficLightDTO(TrafficLight ent);

    List<Automobile> autoDtoToAuto(List<AutomobileDTO> dto);

    List<AutomobileDTO> autoToAutoDTO(List<Automobile> ent);

    List<RoadBlock> blockDtoToBlock(List<RoadBlockDTO> dto);

    List<RoadBlockDTO> blockToBlockDTO(List<RoadBlock> ent);

    List<Line> lineDtoToLine(List<LineDTO> dto);

    List<LineDTO> lineToLineDTO(List<Line> ent);

    List<TrafficLight> trafficLightDtoTotrafficLight(List<TrafficLightDTO> dto);

    List<TrafficLightDTO> trafficLightTotrafficLightDTO(List<TrafficLight> ent);

}
