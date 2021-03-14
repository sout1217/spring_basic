package page_127.app.messages;

// Dto 클래스
public class MessageData {

    private String text;

    public MessageData(String text) {
        this.text = text;
    }

    public MessageData() {
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
