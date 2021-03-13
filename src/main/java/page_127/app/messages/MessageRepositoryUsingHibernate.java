package page_127.app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepositoryUsingHibernate {

    private final static Log log = LogFactory.getLog(MessageRepositoryUsingHibernate.class);

    private SessionFactory sessionFactory;

    public MessageRepositoryUsingHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MessageEntity saveMessage(MessageEntity message) {
        Session session = sessionFactory.openSession();
        session.save(message);
        return message;
    }
}
