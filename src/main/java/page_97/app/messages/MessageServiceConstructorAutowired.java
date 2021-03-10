package page_97.app.messages;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 어노테이션 기반 설정
@Component
public class MessageServiceConstructorAutowired {

    private MessageRepository repository;

    @Autowired
    public MessageServiceConstructorAutowired(MessageRepository repository) {
        this.repository = repository;
    }

    public void save(String text) {
        this.repository.saveMessage(new Message(text));
    }
}
