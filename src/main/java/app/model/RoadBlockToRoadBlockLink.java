package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoadBlockToRoadBlockLink { //??????
    private RoadBlock roadBlock1;
    private RoadBlock roadBlock2;
}
