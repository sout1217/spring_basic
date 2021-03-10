package page_97.app.messages;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        MessageServiceConstructorAutowired messageServiceConstructorAutowired = context.getBean(MessageServiceConstructorAutowired.class);

        messageServiceConstructorAutowired.save("1.생성자 기반 의존성 주입 - @Autowired 활용");

        MessageServiceConstructor messageServiceConstructor = context.getBean(MessageServiceConstructor.class);

        messageServiceConstructor.save("2.생성자 기반 의존성 주입");

        MessageServiceSetterAutowired messageServiceSetterAutowired = context.getBean(MessageServiceSetterAutowired.class);

        messageServiceSetterAutowired.save("3.세터 기반 의존성 주입 - @Autowired 활용");

        MessageServiceSetter messageServiceSetter = context.getBean(MessageServiceSetter.class);

        try {
            messageServiceSetter.save("4.세터 기반 의존성 주입");
        } catch (NullPointerException e) {
            System.out.println(e);
            System.out.println("세터기반 의존성 주입은 어노테이션을 생략 할 수 없습니다");
        }

        MessageServiceFieldAutowired messageServiceFieldAutowired = context.getBean(MessageServiceFieldAutowired.class);

        messageServiceFieldAutowired.save("5.필드 기반 의존성 주입 - @Autowired 활용");

        MessageServiceSetterRequired messageServiceSetterRequired = context.getBean(MessageServiceSetterRequired.class);
        messageServiceSetterRequired.print();

        MessageServiceMethodAutowired messageServiceMethodAutowired = context.getBean(MessageServiceMethodAutowired.class);
        messageServiceConstructor.save("7.메소드 기반 의존성 주입 - @Autowired 활용");

    }
}
