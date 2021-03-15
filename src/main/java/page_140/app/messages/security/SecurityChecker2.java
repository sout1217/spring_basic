package page_140.app.messages.security;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SecurityChecker2 {

    @After("page_135.app.messages.security.SecurityChecker.checkMethodSecurity()")
    public void test() {
        System.out.println("Static Method");
    }
}
