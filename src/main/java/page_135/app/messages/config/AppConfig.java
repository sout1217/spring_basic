package page_135.app.messages.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import page_135.app.messages.security.AuditingFilter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableTransactionManagement
public class AppConfig {

    private DataSource dataSource;

    public AppConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuditingFilter> registraion = new FilterRegistrationBean<>();
        AuditingFilter filter = new AuditingFilter();
        registraion.setFilter(filter);
        registraion.setOrder(Integer.MAX_VALUE);
        registraion.setUrlPatterns(Arrays.asList("/messages/*"));
        return registraion;
    }

    // 하이버네이트에 DataSource 를 주입해준다
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();

        sessionFactoryBean.setDataSource(dataSource);
        sessionFactoryBean.setPackagesToScan("page_135.app.messages");
        return sessionFactoryBean;
    }

    // 하이버네이트 트랜잭션 설정
    @Bean
    public HibernateTransactionManager transactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    /**
     * java.lang.ClassCastException: org.springframework.orm.jpa.EntityManagerHolder cannot be cast to org.springframework.orm.hibernate5.SessionHolder
     *  transactionManager.setSessionFactory(sessionFactory().getObject()); 에서
     *  sessionFactory 를 주었기 때문에 나는 오유 (EntityManager 를 사용해야 한다)
     *
     *  스프링부트가 JPA 의 인터페이스인 EntityManager를 현재 스레드에 등록하는
     *  OpenEntityMangerInViewInterceptor 를 생성하기 때문이다
     *  그리고 repository 에서 하이버네이트의 SessionFactory 를 사용하고 있다
     *  그리고 Transaction Advice 에서 스프링은 EntityManager 를 SessionFactory 로 캐스팅을 시도한다
     *  이 문제를 해결하려면 SessionFactory 대신 EntityManger 를 사용하도록 변경해야 된다
     *
     *  LocalSessionFactoryBean(=SessionFactory) 를 그대로 사용하고 싶다면
     *  application.yml 에서 spring.jpa.open-in-view 를 false 바꾸면 된다 (기본값은 true이다)
     */
}
