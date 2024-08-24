package service;

import dao.TweetDao;
import dao.UserDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.*;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import javax.sql.DataSource;

//@Configuration
//@EnableTransactionManagement
@ComponentScan
@PropertySource("classpath:application.properties")
public class ContextConfiguration {


    @Autowired
    private DataSource dataSource;

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPackagesToScan("entities");
        return sessionFactory;
    }

    @Bean
    @Autowired
    public DataSource dataSource(@Value("${spring.datasource.username}") String userName, @Value("${spring.datasource.password}")String password, @Value("${spring.datasource.url}") String url) {
        DataSourceBuilder dataSource = DataSourceBuilder.create();
        dataSource.username(userName);
        dataSource.password(password);
        dataSource.url(url);
        return dataSource.build();
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
