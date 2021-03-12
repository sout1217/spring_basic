package page_112.app.messages;

import org.springframework.stereotype.Service;

@Service
public class MessageService {

    private MessageRepositoryUsingJDBC repositoryUsingJDBC;

    private MessageRepositoryUsingSpringJDBC repositoryUsingSpringJDBC;

    private MessageRepositoryUsingHibernate repositoryUsingHibernate;

    public MessageService(MessageRepositoryUsingJDBC repositoryUsingJDBC, MessageRepositoryUsingSpringJDBC repositoryUsingSpringJDBC, MessageRepositoryUsingHibernate repositoryUsingHibernate) {
        this.repositoryUsingJDBC = repositoryUsingJDBC;
        this.repositoryUsingSpringJDBC = repositoryUsingSpringJDBC;
        this.repositoryUsingHibernate = repositoryUsingHibernate;
    }

    public Message saveUsingJDBC(String text) {
        return this.repositoryUsingJDBC.saveMessage(new Message(text));
    }

    public Message saveUsingSpringJDBC(String text) {
        return this.repositoryUsingSpringJDBC.saveMessage(new Message(text));
    }

    public MessageEntity saveUsingHibernate(String text) {
        return repositoryUsingHibernate.saveMessage(new MessageEntity(text));
    }
}
