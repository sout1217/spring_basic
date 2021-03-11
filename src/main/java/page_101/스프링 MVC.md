## Spring MVC, 자바 EE Servlet
Spring MVC 는 자바 EE 서블릿 API 기반으로 만들어졌다

서블릿은 일반적으로 톰캣과 같은 애플리케이션 서버인 서블릿 컨네이너 에서 동작한다

서버에 도착하면 일반적으로 **인증, 로깅, 감사**와 같은 필터링 작업을 수행하는 필터 리스트를 통과한다

요청이 모든 필터를 통과하면 애플리케이션 서버는 특정 패턴과 일치하는 URI 를 포함하는 요청을 처리 할 수 있게 등록된 서블릿으로 요청을 넘겨준다

자바 EE 에서 모든 요청(Request)에 대해 **HttpServletRequest** 인스턴스가 생성된다
그리고 모든 HTTP 응답(Response)에 대해 **HttpServletResponse** 인스턴스가 생성된다
여러 요청에서 사용자를 식별하기 위해 애플리케이션 서버는 첫 번째 요청을 받으면 HttpSession 인스턴스를 생성한다

그리고 세션 id 는 HTTP 응답 헤더의 클라이언트에 쿠키로 전송된다

클라이언트는 그 쿠키를 저장하고 다음 요청 시 다시 서버로 보낸다. 이렇게 해서 서버는 쿠키에서 찾은 세션 ID 로 HttpSession 인스턴스를 조회해 사용자를 인식한다

---
자바EE 에서 HttpSessionListener 인터페이스를 구현해 HttpSession 의 라이프 사이클 이벤트를 수신하거나
ServletRequestListener 인터페이스를 구현해 요청에 대한 라이프 사이클 이벤트를 수신하는 리스너를 만들 수 있다

서블릿을 생성하기 위해 **@WebServlet** 어노테이션을 적용하거나 전통방식은 web.xml 파일에 등록할 수 있다

`doGet()` GET 요청처리  
`doPost()` POST 요청처리  
`doPut()` PUT 요청처리  
`doDelete()` DELETE 요청처리

<u>서블릿을 사용할 때 **in-memory** 데이터 또는 **I/O** 수행과 같은 공유 리소스에 접근해야 한다면
서블릿이 항상 동시 요청을 다루고, 하나의 요청에 의한 변경 사항이 다른 요청에 영향을 줄 수 있다는 사실을 기억해야 한다</u>

## DispatcherServlet
<u>스프링 MVC를 사용하면 서블릿을 생성할 필요가 없다</u>
클래스를 생성해 `@Controller` 어노테이션을 추가하고 `@RequestMapping` 어노테이션으로
특정 URI 패턴에 매핑할 수 있다 규약에 따르면 클래스 이름은 보통 Controller로 끝난다

스프링은 요청받기 위해 핵심 서블릿인 **DispatcherServlet** 를 활용한다
이 서블릿은 모든 요청을 처리할 수 있게 설정돼야 하며 `@RequestMapping` 어노테이션에 지정된 URI ㅍ턴에 따라 스프링은 요청을 처리할 패턴에 맞는 컨트롤러를 찾는다

클라이언트 <-> [필터A, 필터B] <-> Dispatcher 서블릿 <-> 컨트롤러

---

Spring 5 에서 Spring Boot 로 넘어왔기 때문에 기존 Maven 에 작성했던
`maven-assembly-plugin` 은 실행할 수 없다

해당 플러그인은 지우고 `spring-boot-mvaen-plugin` 을 작성해주고

cmd 명령어로 `mvn spring-boot:run` 을 이용하여 `mvn install` 실행 없이 사용가능하다

스프링은 기본적으로 별다른 설정이 없으면 String 타입의 반환 값을 요청을 내부적으로 전달해야 하는 경로라고 간주한다.
예를들어 return '/mypage/information.html' 이라고 작성한다면 root/static/mypage/information.html 를 열어주고 응답결과를 넘겨주게 된다

String 타입을 그대로 리턴하기 위해서는 @ResponseBody 를 적어주거나, 클래스 위에 @Controller 대신 @RestController 라고 적어주면 된다