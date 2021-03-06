package page_91.app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

// DB 라고 생각하는 게 이해 빠름
public class MessageRepository {

    // ERROR StatusLogger No log4j2 configuration file found. Using default configuration: logging only errors to the console. Set system property 'log4j2.debug' to show Log4j2 internal initialization logging.
    // 이 에러는 log4j2.xml 파일이 없기 때문에 뜬다
    // resources 폴더 log4j2.xml config 설정을 해주면 문제가 해결된다
    private final static Log log = LogFactory.getLog(MessageRepository.class);

    public void saveMessage(Message message) {
        /*
            <Loggers>
                <Root level="info">
                <AppenderRef ref="console"/>
                </Root>
            </Loggers>
            를 작성해주어야만 로그가 찍힌다

            또는 messages 패키지 로그를 찍히게 하려면 아래처럼 해주면 된다
            <Logger name="page_91.app.messages" level="INFO" additivity="false">
                <AppenderRef ref="console" />
            </Logger>

         */
        log.info("Saved Message: " + message.getText());

    }
}
