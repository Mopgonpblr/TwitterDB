package app.service;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
//@ComponentScan(basePackages = {"app/dao", "app/service"})
//@PropertySource("classpath:application.properties")
public class ContextConfiguration {

    @Bean
    public PlatformTransactionManager transactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public LocalSessionFactoryBean entityManagerFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("app/entities");
        return sessionFactory;
    }
/*
    @Bean
    public DataSource dataSource(@Value("${spring.datasource.username}") String userName, @Value("${spring.datasource.password}")String password, @Value("${spring.datasource.url}") String url) {
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.username(userName);
        dataSource.password(password);
        dataSource.url(url);
        return dataSource.build();
    }

 */

    @Bean
    @ConditionalOnProperty("property.condition")
    public String ThisIsMyFirstConditionalBean() {
        return "This will appear only if @ConditionalOnProperty statement is true";
    }
}
