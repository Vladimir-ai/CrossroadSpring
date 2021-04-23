package app.domain.entity;


import app.domain.DTO.TrafficLightState;
import com.sun.istack.Nullable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Builder;
import lombok.Data;

@Data
@Entity(name="roadblocks")
public class RoadBlock {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToOne
    @Nullable
    private RoadBlock leftBlock;

    @OneToOne
    @Nullable
    private RoadBlock centerBlock;

    @OneToOne
    @Nullable
    private RoadBlock rightBlock;

    @OneToOne
    @Nullable
    private Automobile automobile;

    @Column(name = "trafficLightState", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private TrafficLightState trafficLightState;

    private Boolean isCrossroad;
}
