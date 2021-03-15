package page_135.app.messages.domain.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import page_135.app.messages.domain.repository.MessageRepositoryUsingHibernate;
import page_135.app.messages.security.SecurityCheck;
import page_135.app.messages.domain.entity.MessageEntity;

@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private MessageRepositoryUsingHibernate repositoryUsingHibernate;

    public MessageService(MessageRepositoryUsingHibernate repositoryUsingHibernate) {
        this.repositoryUsingHibernate = repositoryUsingHibernate;
    }

    @SecurityCheck
    @Transactional
    public MessageEntity saveUsingHibernate(String text) {


        // 메시지 DB에 저장
        MessageEntity messageEntity = repositoryUsingHibernate.saveMessage(new MessageEntity(text));

        log.info("New Message [id={}] saved", messageEntity.getId());

        updateStatistics();

        return messageEntity;
    }

    private void updateStatistics() {
        throw new UnsupportedOperationException("이 메소드 'saveUsingHibernate' 는 아직 구현되지 않았습니다");
    }
}
