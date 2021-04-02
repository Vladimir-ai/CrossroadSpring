package app.config;

import app.component.RoadComponent;
import app.repository.impl.AutomobileRepositoryImpl;
import app.repository.impl.LineRepositoryImpl;
import app.repository.impl.RoadBlockRepositoryImpl;
import app.repository.impl.TrafficLightRepositoryImpl;
import app.service.RoadGenerationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {
        LineRepositoryImpl.class,
        RoadComponent.class,
        RoadBlockRepositoryImpl.class,
        AutomobileRepositoryImpl.class,
        TrafficLightRepositoryImpl.class,
        RoadGenerationService.class})
public class AppConfig {
}
