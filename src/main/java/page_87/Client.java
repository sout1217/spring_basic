package page_87;

import page_87.setter_di.RegistrationService;


public class Client {

    public static void main(String[] args) {

        MailSender mailSender = new MailSender("root@gmail.com");

        page_87.constructor_di.RegistrationService contructorDi = new page_87.constructor_di.RegistrationService("root@gmail.com");

        RegistrationService setterDi = new RegistrationService(mailSender);

        // 생성자의 인자로 인스터스를 추가하는 것은 의존성 제어를 하지 못한다

    }
}
