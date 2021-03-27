package app;

import app.component.RoadComponent;
import app.config.AppConfig;
import app.repository.LineRepository;
import app.service.RoadGenerationService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(/*basePackages = "di2"*/basePackageClasses = {AppConfig.class})
public class CrossroadApp {
    //Spring bean != java bean
    //bean руками не создается, di2.config bean - самый важный
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(CrossroadApp.class);
        RoadGenerationService sampleService = context.getBean(RoadGenerationService.class);
        System.out.println("0");
        //sampleService.generateRoad();
        //sampleService.printHello("Jonny");

        //ProductRepository productRepository = context.getBean(ProductRepository.class);

    }

}