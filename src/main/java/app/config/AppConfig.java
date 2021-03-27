package app.config;

import app.component.RoadComponent;
import app.model.Automobile;
import app.repository.AutomobileRepository;
import app.repository.LineRepository;
import app.repository.RoadBlockRepository;
import app.repository.TrafficLightRepository;
import app.service.RoadGenerationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        LineRepository.class,
        RoadComponent.class,
        RoadBlockRepository.class,
        AutomobileRepository.class,
        TrafficLightRepository.class,
        RoadGenerationService.class})
public class AppConfig {
}
