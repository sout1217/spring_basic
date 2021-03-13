package page_127.app.messages;

import org.springframework.stereotype.Service;

@Service
public class MessageService {


    private MessageRepositoryUsingHibernate repositoryUsingHibernate;

    public MessageService(MessageRepositoryUsingHibernate repositoryUsingHibernate) {
        this.repositoryUsingHibernate = repositoryUsingHibernate;
    }

    @SecurityCheck
    public MessageEntity saveUsingHibernate(String text) {
        return repositoryUsingHibernate.saveMessage(new MessageEntity(text));
    }
}
