package app;

import app.domain.Line;
import app.domain.World;
import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApp implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(SpringBootConsoleApp.class);

    public static void main(String[] args) {
        //World world = new World();
        int LINE_COUNT = 12;
        int LINES_PER_SIDE = 3;
        for (int index = 0; index < 4; index++){
            System.out.println(
                    String.format("from %d to %d left",
                            LINES_PER_SIDE * index,
                            LINE_COUNT / 2 + (index < 2 ? index * LINES_PER_SIDE : -(index / 2 + index % 2) * LINES_PER_SIDE)));
            System.out.println(
                    String.format("from %d to %d right",
                            LINES_PER_SIDE * (index + 1) - 1,
                            LINE_COUNT - (index < 2 ? index : index - 2 * (index % 2) + 1) * LINES_PER_SIDE - 1));
        }
//        System.out.println(Arrays.toString(left.getBlockLinksByIndex(51)));
//        LOG.info("Before starting app");
//        SpringApplication.run(SpringBootConsoleApp.class);
//        LOG.info("App started");

    }

    @Override
    public void run(String... args) throws Exception {
        //TO-DO
        LOG.info("Nothing TO-DO");
    }



}
