package page_110.app.messages;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class AppConfig {

    // web.xml 설정을 자바코드 단에서 설정하려면 FilterRegistrationBean 을 사용해야 한다
    @Bean
    public FilterRegistrationBean<AuditingFilter> auditingFilterFilterRegistrationBean() {
        FilterRegistrationBean<AuditingFilter> registraion = new FilterRegistrationBean<>();

        AuditingFilter filter = new AuditingFilter();

        registraion.setFilter(filter); // 필터 등록

        registraion.setOrder(Integer.MAX_VALUE); // FilterChain 에 마지막 위치에 등록

        registraion.setUrlPatterns(Arrays.asList("/messages/*")); // /messages/* url 로 접근하는 경우멘 처리한다

        return registraion;
    }

}
