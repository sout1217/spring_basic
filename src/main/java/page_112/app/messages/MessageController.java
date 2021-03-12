package page_112.app.messages;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/messages")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/welcome")
    public String welcome(Model model) {

        model.addAttribute("message", "모델에 담겨진 메시지 입니다");
        return "welcome";
    }

    @PostMapping("/jdbc")
    @ResponseBody
    public ResponseEntity<Message> saveMessageUsingJDBC(@RequestBody MessageData data) {
        Message saved = messageService.saveUsingJDBC(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/spring-jdbc")
    @ResponseBody
    public ResponseEntity<Message> saveMessageUsingSpringJDBC(@RequestBody MessageData data) {
        Message saved = messageService.saveUsingSpringJDBC(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/hibernate")
    @ResponseBody
    public ResponseEntity<MessageEntity> saveMessage(@RequestBody MessageData data) {
        MessageEntity saved = messageService.saveUsingHibernate(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }

}
