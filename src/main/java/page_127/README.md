# EntityScan 과 ComponentScan

https://perfectacle.github.io/2017/08/03/Spring-boot-study-002day/


|어노테이션|컬럼1|컬럼2|
|---|---|---|
|@CompoentScan|@Component, @Controller, @RestController, @Service, @Repository 등 스캔 할 패키지를 지정할 때 사용|
|@EntityScan|@Entity 어노테이션 스캔할 패키지를 지정할 때 사용|

--- 

@SpringBootApplication 안에 @ComponentScans 이 있으며
@SpringBootApplication 매개변수로 베이스패키지 설정을 하면 @ComponentScans 에 전달된다

### Application.java 에서 어노테이션으로 설정
```java
@SpringBootApplication(scanBasePackages = {"page_127.app.messages"})
@EntityScan(basePackages = {"page_127.app.messages"})
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}

```
---
### @Configuration 클래스에서 설정
```java
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
        sessionFactoryBean.setPackagesToScan("page_127.app.messages"); // 엔티티 스캔할 패키지를 설정 해주어야 한다
        return sessionFactoryBean;
    }

}
```

[README2 - Spring AOP ](./README2.md)  
[README3 - @Annotation](./README3.md)
