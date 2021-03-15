package page_140.app.messages.domain.repository;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import page_140.app.messages.domain.entity.MessageEntity;

import java.util.List;

@Repository
public class MessageRepositoryUsingHibernate {

    private final static Log log = LogFactory.getLog(MessageRepositoryUsingHibernate.class);

    private SessionFactory sessionFactory;

    public MessageRepositoryUsingHibernate(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public MessageEntity saveMessage(MessageEntity message) {
        Session session = sessionFactory.getCurrentSession();
        session.save(message);
        return message;
    }

    /**
     * HQL(Hibernate Query Language)
     */
    public List<MessageEntity> getMessages() {

        Session session = sessionFactory.getCurrentSession();

//        String hql = "select m from MessageEntity as m";
        String hql = "from MessageEntity";
        Query<MessageEntity> query = session.createQuery(hql, MessageEntity.class);
        return query.list();
    }
}
