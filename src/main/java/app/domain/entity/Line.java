package app.domain.entity;

import app.domain.DTO.RoadBlockDTO;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "lines")
@NoArgsConstructor
public class Line {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "lineLength")
    private Integer lineLength;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    private RoadBlock startBlock;

    public Line(RoadBlock startBlock, int lineLength){
        this.startBlock = startBlock;
        this.lineLength = lineLength;
    }
}
