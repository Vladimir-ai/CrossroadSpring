package app.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Line {
    private boolean isPositiveWay;
    private List<Automobile> automobileList;
}
