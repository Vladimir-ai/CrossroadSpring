package app.domain.entity;

import app.domain.DTO.RoadBlockDTO;
import app.domain.DTO.TrafficLightState;
import com.sun.istack.NotNull;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.Data;

@Data
@Entity(name = "trafficLight")
public class TrafficLight {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "trafficLightState", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TrafficLightState currentState;

    @OneToMany(mappedBy = "id")
    private List<RoadBlock> controlledBlocks;

    @Column(name = "lastSwitchTime")
    private Long lastSwitchTime;

    @Column(name = "cycleTimeRed")
    private Long cycleTimeRed;

    @Column(name = "cycleTimeYellow")
    private Long cycleTimeYellow;

    @Column(name = "cycleTimeGreen")
    private Long cycleTimeGreen;

}
