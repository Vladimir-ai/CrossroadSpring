package app.config;

import java.util.Properties;
import javax.activation.DataSource;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

@Configuration
public class HibernateConfig {
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
        sessionFactoryBean.setPackagesToScan("app.domain.entity");
        sessionFactoryBean.setHibernateProperties(hibernateProps());
        return sessionFactoryBean;
    }


    private final Properties hibernateProps() {
        return new org.hibernate.cfg.Configuration().getProperties();
    }
}
