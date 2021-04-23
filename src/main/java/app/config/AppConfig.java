package app.config;

import org.hibernate.SessionFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "app")
public class AppConfig {


    public void a(){
        SessionFactory factory = new org.hibernate.cfg.
                Configuration().configure().buildSessionFactory();

        factory.createEntityManager().createQuery("SELECT id from roadblocks");

    }
}
