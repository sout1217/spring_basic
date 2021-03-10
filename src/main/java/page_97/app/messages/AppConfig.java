package page_97.app.messages;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("page_97.app.messages") // 패키지명을 바꿨기 때문에 이 부분도 97 로 수정 필수 (해당 패키지에서 컴포넌트를 찾음)
public class AppConfig {

    // MessageRepository 클래스와 MessageSerivec 클래스에 어노테이션 기반설정인 @Component 를 사용하여
    // @Bean 으로 생성할 필요성이 사라졌다

    // 만약 @Bean 으로 설정하고 @Component 로 설정도 한다면,
    // @Bean 으로 재정의 된다


    // @Required 는 필수이기 때문에 미리 필수로 정의해야만 사용이 가능하다
    @Bean
    public MessageServiceSetterRequired messageServiceSetterRequired() {
        MessageServiceSetterRequired messageServiceSetterRequired = new MessageServiceSetterRequired();
        messageServiceSetterRequired.setText("6.세터 기반 의존성 주입 - @Required 활용");
        return messageServiceSetterRequired;
    }
}
