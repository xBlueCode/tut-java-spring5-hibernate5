package xbc.tut.config;


import java.util.Properties;
import javax.sql.DataSource;
import org.hibernate.cfg.AvailableSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = {"xbc.tut"})
@PropertySource({ "classpath:app.properties" })
public class HibernateConfig {

  @Autowired
  private Environment env;

  private String PACKAGES_TO_SCAN = "xbc.tut.model" ;

  @Bean
  public LocalSessionFactoryBean sessionFactory() {

    LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();

    sessionFactory.setDataSource(dataSource());
    sessionFactory.setPackagesToScan(PACKAGES_TO_SCAN);
    sessionFactory.setHibernateProperties(hibernateProperties());

    return sessionFactory;
  }

  @Bean
  public DataSource dataSource(){

    DriverManagerDataSource dataSource = new DriverManagerDataSource();

    dataSource.setDriverClassName(env.getProperty("datasource.driver"));
    dataSource.setUrl(env.getProperty("datasource.url"));

    // specify username & password
    dataSource.setUsername(env.getProperty("datasource.username"));
    dataSource.setPassword(env.getProperty("datasource.password"));

    return dataSource;
  }


  @Bean
  public PlatformTransactionManager hibernateTransactionManager(){

    HibernateTransactionManager transactionManager = new HibernateTransactionManager();

    transactionManager.setSessionFactory(sessionFactory().getObject());

    return transactionManager;
  }


  private final Properties hibernateProperties(){

    Properties hibernateProperties = new Properties();

    hibernateProperties.setProperty(
        AvailableSettings.DIALECT,
        env.getProperty("hibernate.dialect"));
    hibernateProperties.setProperty(
        AvailableSettings.HBM2DDL_AUTO,
        env.getProperty("hibernate.hbm2ddl.auto"));
    hibernateProperties.setProperty(
        AvailableSettings.SHOW_SQL,
        env.getProperty("hibernate.show_sql"));
//    hibernateProperties.setProperty(
//        AvailableSettings.CURRENT_SESSION_CONTEXT_CLASS,
//        env.getProperty("hibernate.current.session.context.class"));

    return hibernateProperties;
  }

}
