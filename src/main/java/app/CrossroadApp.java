package app;

import app.config.AppConfig;
import app.model.Automobile;
import app.model.RoadBlock;
import app.model.TrafficLight;
import app.model.TrafficLightState;
import app.service.CarGenerationService;
import app.service.CarMovingService;
import app.service.RoadGenerationService;
import app.service.TrafficLightService;
import java.time.Instant;
import java.util.List;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@Configuration
@ComponentScan(/*basePackages = "di2"*/basePackageClasses = {AppConfig.class})
public class CrossroadApp {

    public static void main(String[] args) {

        run();
        //ProductRepository productRepository = context.getBean(ProductRepository.class);

    }

    private static void run() {
        ApplicationContext context = new AnnotationConfigApplicationContext(CrossroadApp.class);
        RoadGenerationService roadService = context.getBean(RoadGenerationService.class);
        CarGenerationService carGenerationService = context.getBean(CarGenerationService.class);
        CarMovingService carMovingService = context.getBean(CarMovingService.class);
        TrafficLightService trafficLightService = context.getBean(TrafficLightService.class);

        if (!roadService.isRoadInitiated())
            roadService.initRoad();

        System.out.println("Road Generated");

        trafficLightService.startAll();
        trafficLightService.changeCycleTimeByColor(TrafficLightState.RED, 5);
        trafficLightService.changeCycleTimeByColor(TrafficLightState.YELLOW, 1);
        trafficLightService.changeCycleTimeByColor(TrafficLightState.GREEN, 10);
        System.out.println("Traffic Lights are initiated");

        carGenerationService.generateCars(8);

        for (int i = 0; i < 101; i++) {
            trafficLightService.changeStateByTime();
            System.out.println("Traffic lights state was checked\n");
            printAllTrafficLights(trafficLightService.getTrafficLightList());

            System.out.println("Before moving: ");
            printCarsToConsole(carMovingService.getAllAutomobiles());

            carMovingService.moveAllCars();

            System.out.println("After moving: ");
            printCarsToConsole(carMovingService.getAllAutomobiles());


            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void printCarsToConsole(List<Automobile> cars) {
        System.out.println("Automobile list:");
        cars.forEach(auto -> {
            System.out.println("auto with ID " + auto.getId().toString() + " stands on road block with ID " + auto.getRoadBlock().getId().toString() + "\n");
        });
    }

    private static void printAllTrafficLights(List<TrafficLight> trafficLights) {
        System.out.println("Traffic light list: ");
        trafficLights.forEach(trafficLight -> {
            System.out.println("trafficLight with ID " + trafficLight.getId().toString()
                    + " has state " + trafficLight.getCurrentState().toString() + "\n");
        });
    }

}