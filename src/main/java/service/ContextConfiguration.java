package service;

import dao.TweetDao;
import dao.UserDao;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
@ComponentScan
@PropertySource("classpath:application.properties")
public class ContextConfiguration {

    @Bean
    public LocalSessionFactoryBean sessionFactory(DataSource dataSource) {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("entities");
        return sessionFactory;
    }

    @Bean
    public DataSource dataSource(@Value("${spring.datasource.username}") String userName, @Value("${spring.datasource.password}")String password, @Value("${spring.datasource.url}") String url) {
        PGSimpleDataSource dataSource = new PGSimpleDataSource();
        dataSource.setUser(userName);
        dataSource.setPassword(password);
        dataSource.setURL(url);
        return dataSource;
    }

    @Bean
    public PlatformTransactionManager hibernateTransactionManager(SessionFactory sessionFactory) {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);
        return transactionManager;
    }

    @Bean
    public UserDao userDao(){
        return new UserDao();
    }

    @Bean
    public TweetDao tweetDao(){
        return new TweetDao();
    }

}
