package page_127.app.messages;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Date;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;


@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {MessageController.class})
class MessageControllerTests {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private MessageService messageService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    void messageControllerTest() throws Exception {

        String sendText = "테스트 메시지 입니다";

        MessageEntity mockEntity = new MessageEntity(sendText);
        mockEntity.setId(10);
        mockEntity.setCreatedDate(new Date());

        given(messageService.saveUsingHibernate(sendText)).willReturn(mockEntity);

        String jsonString = mapper.writeValueAsString(mockEntity);

        mvc.perform(MockMvcRequestBuilders.post("/messages/hibernate")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonString)
                )
                .andDo(print())
                .andExpect(jsonPath("$.text").value("테스트 메시지 입니다"))
                .andExpect(jsonPath("$.id").value(10))
                .andExpect(jsonPath("$.createdDate").value(new Date()))
                ;
    }

}