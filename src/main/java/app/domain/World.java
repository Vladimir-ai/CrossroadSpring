package app.domain;

import lombok.Data;

import java.util.List;

@Data
public class World {
    private Integer width;
    private Integer height;
    private List<Road> roadList;
}
