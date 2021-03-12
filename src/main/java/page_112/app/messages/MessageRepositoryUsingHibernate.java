package page_112.app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

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
