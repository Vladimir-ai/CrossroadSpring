package app.config;

import app.component.RoadComponent;
import app.repository.LineRepository;
import app.service.RoadGenerationService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = {RoadGenerationService.class, LineRepository.class, RoadComponent.class})
public class AppConfig {
}
