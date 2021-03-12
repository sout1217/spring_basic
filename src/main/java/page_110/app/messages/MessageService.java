package page_110.app.messages;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private MessageRepository repository;

    public MessageService(MessageRepository repository) {
        this.repository = repository;
    }

    public void save(String text) {
        this.repository.saveMessage(new Message(text));
    }
}
