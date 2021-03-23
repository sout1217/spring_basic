# Session Factory

```java
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
```
이전 커밋에서 이렇게 작성했더니 post 요청을 해서 에러가 나면
roll back 처리가 되어야 하는데 되지 않았다

그 이유는 서로다른 이유는 아래를 참고 그림 참고

![트랜잭션 세션](./트랜잭션%20세션에%20대한%20이해.png)
- - -
##결론
Repository 에서 `Session session = sessionFacotry.getCurrentSession()` 을 하면 된다

##**※주의**  
MessageService 메소드에 @Transactional 는 필수적으로 달아주어야 한다
안그러면 MessageRepository 에서 getCurrentSession() 에서 오류를 발생한다

`org.hibernate.HibernateException: Could not obtain transaction-synchronized Session for current thread`

