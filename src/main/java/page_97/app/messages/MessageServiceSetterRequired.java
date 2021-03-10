package page_97.app.messages;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Required;

public class MessageServiceSetterRequired {

    private String text;
    private final static Log log = LogFactory.getLog(MessageServiceSetterRequired.class);

    public void print() {
        log.info(this.text);
    }

    @Required
    public void setText(String text) {
        this.text = text;
    }
}
