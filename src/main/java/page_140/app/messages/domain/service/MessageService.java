package page_140.app.messages.domain.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import page_140.app.messages.domain.entity.MessageEntity;
import page_140.app.messages.domain.repository.MessageRepositoryUsingHibernate;
import page_140.app.messages.security.SecurityCheck;

import java.util.List;


@Service
public class MessageService {

    private static final Logger log = LoggerFactory.getLogger(MessageService.class);

    private MessageRepositoryUsingHibernate repositoryUsingHibernate;

    public MessageService(MessageRepositoryUsingHibernate repositoryUsingHibernate) {
        this.repositoryUsingHibernate = repositoryUsingHibernate;
    }

    /** 트랜잭션으로 롤백처리 */
    @SecurityCheck
    @Transactional
    public MessageEntity saveUsingHibernate(String text) {

        // 메시지 DB에 저장
        MessageEntity messageEntity = repositoryUsingHibernate.saveMessage(new MessageEntity(text));

        log.info("New Message [id={}] saved", messageEntity.getId());

        updateStatistics();

        return messageEntity;
    }

    /** 트랜잭션 롤백처리 안함 - 특정 예외클래스 */
    @SecurityCheck
    @Transactional(noRollbackFor = {UnsupportedOperationException.class})
    public MessageEntity saveNoRollBackUsingHibernate(String text) {

        // 메시지 DB에 저장
        MessageEntity messageEntity = repositoryUsingHibernate.saveMessage(new MessageEntity(text));

        log.info("New Message [id={}] saved", messageEntity.getId());

        updateStatistics();

        return messageEntity;
    }

    private void updateStatistics() {
        throw new UnsupportedOperationException("이 메소드 'saveUsingHibernate' 는 아직 구현되지 않았습니다");
    }

    @Transactional(readOnly = true)
    public List<MessageEntity> getMessages() {
        return repositoryUsingHibernate.getMessages();
    }

    @Transactional
    public MessageEntity saveMessage(String text) {
        return repositoryUsingHibernate.saveMessage(new MessageEntity(text));
    }

    @Transactional
    public void deleteMessage(Long id) {
        MessageEntity messageEntity = repositoryUsingHibernate.findById(id);
        repositoryUsingHibernate.deleteMessage(messageEntity);
    }
}
