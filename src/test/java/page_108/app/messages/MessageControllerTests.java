package page_108.app.messages;

import com.samskivert.mustache.Mustache;
import com.samskivert.mustache.Template;
import org.junit.jupiter.api.Test;
import org.springframework.boot.autoconfigure.mustache.MustacheResourceTemplateLoader;

import java.io.Reader;
import java.util.HashMap;
import java.util.Map;

//@ExtendWith({SpringExtension.class})
class MessageControllerTests {


    // https://github.com/samskivert/jmustache
    @Test
    void String() {
        String html = "<html><body><p>{{message}}</p><body></html>";

        Template template = Mustache.compiler().compile(html);

        Map<String, String> model = new HashMap<>();

        model.put("message", "담겨진 모델입니다");

        System.out.println(template.execute(model));
    }

    @Test
    void FileLoader() throws Exception {

//        ClassPathResource classPathResource = new ClassPathResource("templates/welcome.mustache");
//        Path path = Paths.get(classPathResource.getURI());
//        System.out.println(path);

        MustacheResourceTemplateLoader mustacheResourceTemplateLoader = new MustacheResourceTemplateLoader();
        Reader reader = mustacheResourceTemplateLoader.getTemplate("templates/welcome.mustache");

        Template template = Mustache.compiler().compile(reader);

        Map<String, String> model = new HashMap<>();

        model.put("message", "메시지입니다");

        System.out.println(template.execute(model));
    }
}