package page_127.app.messages;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class SecurityChecker {

    private static final Logger logger = LoggerFactory.getLogger(SecurityChecker.class);

    @Pointcut("@annotation(SecurityCheck)")
    public static void checkMethodSecurity() {}

    @Before("checkMethodSecurity()")
    public void checkSecurity() {
        logger.info("체크 시큐리티 작동");
    }

    @Around("checkMethodSecurity()")
    public Object checkSecurity2(ProceedingJoinPoint pjp) throws Throwable {
        Object o = pjp.proceed();

        MessageEntity messageEntity = (MessageEntity) o;

        System.out.println(messageEntity.getText());

        return new MessageEntity("AOP 에서 가로채 리턴된 메시지 입니다!");
    }

}
