# Filter

커스텀 필터를 만들기위해서는 **GenericFilterBean** 추상클래스를 상속받아야 한다


 ```java
public class AuditingFilter extends GenericFilterBean {

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

        long start = new Date().getTime();

        chain.doFilter(req, res);

        long elapsed = new Date().getTime()- start;

        HttpServletRequest request = (HttpServletRequest) req;

        logger.debug(String.format("요청[uri= %s, method= %s] completed in %d ms", request.getRequestURI(), request.getMethod(), elapsed));
    }
}
```

- Filter 체인이 모든 Filter 를 담고 있으며  해당 필터에서 req 또는 res 의 설정을 조금 씩 바꾸어 chain.doFilter(req,res) 를 이용해 다음 필터를 수정하게 되어 역할을 나눌 수 있게 된다
    - 예를들어 A-Filter 에서 사용자를 식별하기 위해 request 에 session 을 만들고 B-Filter 에서는 session 이 있는지 확인한 후 해당 url 로 접근처리 하는 식으로 역할을 나눌 수 있다

- GenericFilterBean 클래스에는 logger 가 이미 static 필드로 있기때문에 바로 사용이 가능하다

- 처음에 웹에 접근했을 때 로그에 뜨는 속도는 337ms 이상 나오지만 새로고침을 이용해서 다시 접근하면, 속도가 내려가 있을 것 이다
- 이것을 때로는 <u>서버 예열(Server Warming Up)</u> 이라고 부른다 (한 번 신뢰할 수 있는 사용자에 대해 빠른 처리를 하게 끔 설계 되어있다)

- 스프링 MVC 이외에 스프링 WebFlux 웹스택이 있는 데, 이것은 아주 많은 양의 동시 연결을 처리를 하기 위해 만들어진 논블로킹(non-blocking) 웹 프레임워크 이다


**[Flux 패턴에 관한글](https://beomy.tistory.com/44)**

리액트, 앵귤러와 같은 경우 Flux 가 필요하다

