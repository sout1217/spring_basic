package page_91.app.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("page_91.app.messages")
public class AppConfig {

    // @Bean 방식은 간단한 구조를 짤 때 사용되며, 복잡한 구조를 만들때는 비효율적이다
    // 예를들어 Repository 가 20개 30개 있을 때, 자바코드로 작성하기에는 가독성이 떨어져 한 번에 구조를 이해하는데는 어렵다
    // 때문에 @Component, @Service, @Controller, @Repository 라는 어노테이션을 사용하여 스프링 컨텍스트가 자동으로 연결을 맺도록 하고 있다
    // @Component 는 제네럭타입으로 설계되어 해당클래스로 인스턴스화 된다. - 때문에 어떠한 클래스라도 스프링 컨텍스트에서 관리할 수 있다

    @Bean()
    public MessageRepository messageRepository() {
        return new MessageRepository();
    }

    @Bean("customMessageService")
    MessageService messageService() {
        return new MessageService(messageRepository());
    }
}
