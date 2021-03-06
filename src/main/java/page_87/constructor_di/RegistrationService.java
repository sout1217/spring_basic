package page_87.constructor_di;

import page_87.MailSender;

public class RegistrationService {

    private MailSender mailSender;

    public RegistrationService(String data) {
        this.mailSender = new MailSender(data);
    }

}
