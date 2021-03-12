package page_112.app.messages;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
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
        sessionFactoryBean.setPackagesToScan("page_112.app.messages");
        return sessionFactoryBean;
    }

}
