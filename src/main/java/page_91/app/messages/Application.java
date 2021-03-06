package page_91.app.messages;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        // 스프링 컨텍스트 (관리) 중 어노테이션을 이용한 컨텍스트를 이용하여 실행하며
        // 콘픽(설정)방식은 AppConfig.class 를 이용해라
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        // AppConfig.class 에서 인스턴스화 된 MessageRepository 와 MessageService 클래스를
        // @Bean 어노테이션을 이용하여 스프링 컨텍스트에서 관리 할 수 있게 된다
        MessageService messageService = context.getBean(MessageService.class);

        // @Bean 으로 스프링 컨텍스트에서 인스턴스들을 관리하고 있기 때문에
        // context 를 이용하여 해당 인스턴스에 접근이 가능하다
        // 아래는 인스턴스화 된 messageService 에 save 메소드를 하여
        // messageService 에서 출력을 하게끔 한다
        messageService.save("Hello, Spring");
    }
}
