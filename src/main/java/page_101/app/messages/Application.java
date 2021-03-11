package page_101.app.messages;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// SpringBootApplication 어노테이션 안에 @ComponentScan 기능이 있기 때문에 AppConfig 에서 지워도 작동할 수 있다
@SpringBootApplication
public class Application {

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);

    }
}
