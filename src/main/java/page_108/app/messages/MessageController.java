package page_108.app.messages;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/messages")
public class MessageController {

    @GetMapping("/welcome")
    public String welcome(Model model) {

        model.addAttribute("message", "모델에 담겨진 메시지 입니다");
        return "welcome";
    }

}
