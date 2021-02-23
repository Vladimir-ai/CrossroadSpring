package app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootConsoleApp implements CommandLineRunner {
    private static Logger LOG = LoggerFactory.getLogger(SpringBootConsoleApp.class);

    public static void main(String[] args) {
        LOG.info("Before starting app");
        SpringApplication.run(SpringBootConsoleApp.class);
        LOG.info("App started");
    }

    @Override
    public void run(String... args) throws Exception {
        //TO-DO
        LOG.info("Nothing TO-DO");
    }



}
