package page_87.setter_di;

import page_87.MailSender;

public class RegistrationService {

    private MailSender mailSender;

    public RegistrationService(MailSender mailSender) {
        this.mailSender = mailSender;
    }
}


