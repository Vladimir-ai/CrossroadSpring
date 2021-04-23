package app.domain.entity;


import app.domain.DTO.TrafficLightState;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;

@Data
@Entity(name="roadblocks")
public class RoadBlock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    private RoadBlock leftBlock;

    @OneToOne
    private RoadBlock centerBlock;

    @OneToOne
    private RoadBlock rightBlock;

    @OneToOne
    private Automobile automobile;

    @Column(name = "trafficLightState", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TrafficLightState trafficLightState;

    private Boolean isCrossroad;
}
