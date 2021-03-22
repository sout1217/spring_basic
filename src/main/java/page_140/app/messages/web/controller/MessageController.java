package page_140.app.messages.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import page_140.app.messages.domain.dto.MessageData;
import page_140.app.messages.domain.entity.MessageEntity;
import page_140.app.messages.domain.service.MessageService;

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

    @PostMapping("/hibernate")
    @ResponseBody
    public ResponseEntity<MessageEntity> saveMessage(@RequestBody MessageData data) {

        MessageEntity saved = messageService.saveUsingHibernate(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }

    @PostMapping("/hibernate-norollback")
    @ResponseBody
    public ResponseEntity<MessageEntity> saveMessageNoRollback(@RequestBody MessageData data) {

        MessageEntity saved = messageService.saveNoRollBackUsingHibernate(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }
}
