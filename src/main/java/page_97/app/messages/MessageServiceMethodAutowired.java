package page_97.app.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 어노테이션 기반 설정
@Component
public class MessageServiceMethodAutowired {

    private MessageRepository repository;

    public void save(String text) {
        this.repository.saveMessage(new Message(text));
    }

    // 사용자 정의 메소드를 이용한 의존성 주입 방법
    @Autowired
    public void customMethodDependency(MessageRepository repository) {
        this.repository = repository;
    }
}
