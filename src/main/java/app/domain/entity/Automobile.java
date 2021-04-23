package app.domain.entity;

import app.domain.DTO.DriveModel;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Entity(name ="automobiles")
public class Automobile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "speed")
    private Integer speed;

    @Column(name = "driveModel", nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private DriveModel driveModel;

    @OneToOne
    @Nullable
    private RoadBlock roadBlock;

    private Boolean hasTurned;
}
