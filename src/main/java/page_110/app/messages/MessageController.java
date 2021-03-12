package page_110.app.messages;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MessageController {

    @GetMapping("/messages/welcome")
    public String welcome(Model model) {

        model.addAttribute("message", "모델에 담겨진 메시지 입니다");
        return "welcome";
    }

    @ResponseBody
    @GetMapping("/index")
    public String index() {
        return "이 URL 에서는 AuditingFilter 를 실행하지 않습니다";
    }


}
