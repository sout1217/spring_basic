package page_112.app.messages;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "messages")
public class MessageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "text", nullable = false, length = 128)
    private String text;

    @CreationTimestamp
    @Column(name="created_date", nullable = false)
    private Date createdDate;

    public MessageEntity() {
    }

    public MessageEntity(String text) {
        this.text = text;
    }

    public Integer getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }
}
