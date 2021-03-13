package page_127.app.messages;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityChecker2 {

    @After("SecurityChecker.checkMethodSecurity()")
    public void test() {
        System.out.println("Static Method");
    }
}
