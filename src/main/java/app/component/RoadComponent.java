package app.component;

import app.model.RoadBlock;
import app.repository.RoadBlockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RoadComponent {

    private static final int LINES_PER_SIDE = 2;
    private static final int LINE_LENGTH = 100;

    private final RoadBlockRepository roadBlockRepository;

    @Autowired
    public RoadComponent(RoadBlockRepository roadBlockRepository) {
        this.roadBlockRepository = roadBlockRepository;
    }

    public int getLinesPerSide(){
        return LINES_PER_SIDE;
    }

    public int getLineLength(){
        return LINE_LENGTH;
    }

}
