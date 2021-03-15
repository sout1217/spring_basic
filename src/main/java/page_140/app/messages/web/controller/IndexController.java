package page_140.app.messages.web.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import page_140.app.messages.domain.dto.MessageData;
import page_140.app.messages.domain.entity.MessageEntity;
import page_140.app.messages.domain.service.MessageService;

import java.util.List;

@Controller
@RequestMapping("/")
public class IndexController {

    private MessageService messageService;

    public IndexController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public String index() {
        return "home";
    }

    @ResponseBody
    @GetMapping("/api/messages")
    public ResponseEntity<List<MessageEntity>> getMessages() {

        List<MessageEntity> messages = messageService.getMessages();

        return ResponseEntity.ok(messages);
    }

    @ResponseBody
    @PostMapping("/api/messages")
    public ResponseEntity<MessageEntity> save(@RequestBody MessageData messageData) {
        MessageEntity messageEntity = messageService.saveMessage(messageData.getText());
        return ResponseEntity.ok(messageEntity);
    }

    @ResponseBody
    @DeleteMapping("/api/messages/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {

        messageService.deleteMessage(id);

        return ResponseEntity.ok(String.format("%d deleted !", id));
    }
}
