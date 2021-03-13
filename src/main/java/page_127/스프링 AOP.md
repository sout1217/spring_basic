보안 검사가 부족하다

현재 누구나 /message (POST) API 를 통해 메시지를 게시 할 수 있다

다음은 API 핸들러인 MessageController.saveMessage() 메소드에 보안 검사 로직을  추가하도록 간단하게 수정한 코드다

```java
package page_127.app.messages;

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

    @PostMapping("/hibernate")
    @ResponseBody
    public ResponseEntity<MessageEntity> saveMessage(@RequestBody MessageData data) {
        
        checkSecurity();

        MessageEntity saved = messageService.saveUsingHibernate(data.getText());

        if (saved == null) {
            return ResponseEntity.status(500).build();
        }

        return ResponseEntity.ok(saved);
    }
    public void checkSecurity() throws NotAuthorizedException {
        // 보안검사 수행 로직
    }
}
``` 

이 방식은 큰 문제 없이 잘 동작할 것이다. 그러나 모든 API 핸들러의 시작 부분에서
위 코드 처럼 불필요하게 중복되는 코드를 보게 되므로 보안 검사를 실행해야 하는 수십 개의 API를 가진 
애플리케이션에서는 코드 중복이 발생할 수 있다

중앙에서 보안 검사를 수행하는 것이 바람직하며 코드 실행이 API 핸들러에 도달했을 때 보안은 이미 확인된 것 이다. API 핸들러부터는 더 이상 걱정하지 않아도 된다

요청에 대한 보안 검사를 담당하는 `SecurityFilter` 를 생성할 수 있다

복잡한 애플리케이션에서는 <u>메소드 단계에서</u> 보안 검사를 수행하기를 원할 수 있다. 예를들어 MessageService.save() 메소드에서
추가 보안 검사를 수행 할 수 있다. 이 경우 Filter 에 보안 검사를 수행하면 동작하지 않을 것이다. AOP 기술을 사용해야 한다

AOP 는 객체 지향 프로그래밍과 비교했을 떄 애플리케이션 구조를 바라보는 또 다른 관점이라고 볼 수 있다
예제 메시지 앱에서 AOP 로 보안 검사를 구현하기 전에 AOP의 기본 개념을 소개한다


### 관심사
AOP 에서 보안 검사는 보안에 관련된 관심사(Concerns) 또는 애플리케이션이 충족해야 하는 목표로 생각할 수 있다
일반적으로 관심사는 여러 클래스에 걸쳐 있다. 다른 유형의 관심사로는 다음 섹션에서 설명한 애플리케이션 <u>서비스 API의 성능 로깅 트랜잭션</u> 등이 있다

### 애스팩트 (Aspects)
애스펙트 (Aspects) 은 이러한 관심사를 모듈화한 것이다. 
여러 클래스에 걸쳐 코드를 분산하는 대신에 관심사를 다루는 로직을 하나의
애스팩트에 넣는다 스프링 AOP 에서는 일반 클래스에 애스펙트를 구현 할 수 있고
AspectJ 의 어노테이션인 @Aspect 어노테이션을 적용할 수 있다

Class A 의 methodA(), classB 의 methodB(), classC의 methodC()가 있고, 이 모든 메소드 내부에서 보안검사가
필요하다고 가정하자. AOP를 사용하기 전에는 이 모든 메소드에 보안 검사를 처리하는 코드를 추가해야한다
그리고 다른 메소드에서 보안 검사를 실행해야 한다면 해당 메소드에도 checkSecurity() 를 추가 해야 한다
AOP를 이용하면 보안 검사 로직을 일반 자바 클래스인 SecurityChecker 애스펙트로 뽑아 내면 된다


### AOP 문법
 
|명시자|설명|
|---|---|
|execution|Advice를 적용할 메서드를 명시할 때 사용|
|within|특정 타입에 속하는 메서드를 JoinPoint로 설정되도록 명시 할 때 사용|
|bean|스프링 2.5 버전 부터 지원하기 시작 스프링 빈을 이용하여 JoinPoint 를 사용|

> ### execusion 명시자

`execusion([접근제어자] 리턴타입 [패키지명+클래스이름].메소드명(파라미터명))`

|명칭|내용|형태|비고|
|---|---|---|---|
|접근 제어자|접근 제어자|public, protected, private|생략가능|
|리턴타입|리턴 타입을 명시|int, Integer, long, Long|
|패키지명+클래스이름|패키지와 클래스|com.app.messages.MessageController|생략가능|
|메소드명|메소드명|setMessage|
|파라미터|파라미터명|text|
    
"execusion([접근제어자] 리턴타입 [패키지명+클래스명].메소드명(파라미터명))"
    
1.execution(public Integer com.edu.aop.*.*(*))
- com.edu.aop 패키지에 속해있고, 파라미터가 1개인 모든 메서드

2.execution(* com.edu..*.get*(..))
- com.edu 패키지 및 하위 패키지에 속해있고, 이름이 get으로 시작하는 파라미터가 0개 이상인 모든 메서드
  
3.execution(* com.edu.aop..*Service.*(..))
- com.edu.aop 패키지 및 하위 패키지에 속해있고, 이름이 Service르 끝나는 인터페이스의 파라미터가 0개 이상인 모든 메서드

4.execution(* com.edu.aop.BoardService.*(..))
- com.edu.aop.BoardService 인터페이스에 속한 파마리터가 0개 이상인 모든 메서드

5.execution(* some*(*, *))
- 메서드 이름이 some으로 시작하고 파라미터가 2개인 모든 메서드

> ### within 명시자
6.within(com.edu.aop.SomeService)
- com.edu.aop.SomeService 인터페이스의 모든 메서드

7.within(com.edu.aop.*)
- com.edu.aop 패키지의 모든 메서드

8.within(com.edu.aop..*)
- com.edu.aop 패키지 및 하위 패키지의 모든 메서드
 
> ### bean 명시자
9.bean(someBean)
- 이름이 someBean인 빈의 모든 메서드
 
10.bean(some*)
- 빈의 이름이 some으로 시작하는 빈의 모든 메서드

@Before : 메소드 실행 전 동작
@After : 메소드 실행 후 동작
@After-returning : 메소드가 정상적으로 실행 된 후 동작
@After-throwing : 예외가 발생한 후 동작
@Around : 메소드 호출 이전, 이후, 예외 발생 등 모든 시점에서 동작

---

조인포인트(Joinpoint) : 
- 클라이언트가 호출하는 모든 비즈니스 메소드, 조인포인트 중에서 포인트컷되기 때문에 포인트컷의 후보로 생각할 수 있다.

포인트컷(Pointcut) :
- 특정 조건에 의해 필터링된 조인포인트, 수많은 조인포인트 중에 특정 메소드에서만 횡단 공통기능을 수행시키기 위해서 사용한다.

표현식 :
- 리턴타입 패키지경로 클래스명 메소드명(매개변수)

어드바이스(Advice) : 
- 횡단 관심에 해당하는 공통 기능의 코드, 독립된 클래스의 메소드로 작성한다

위빙(Weaving) :
- 포인트컷으로 지정한 핵심 관심 메소드가 호출될 때, 어드바이스에 해당하는 횡단 관심 메소드가 삽입되는 과정을 의미한다. 이를 통해 비즈니스 메소드를 수정하지 않고도 횡단 관심에 해당하는 기능을 추가하거나 변경이 가능해진다.

애스팩트(Aspect) : 
- 포인트컷과 어드바이스의 결합이다. 어떤 포인트컷 메소드에 대해 어떤 어드바이스 메소드를 실행할지 결정한다.

>요약
```java
@Aspect // 애스펙트
@Component
class Aop() {

    // 포인트 컷
    // @Pointcut("@annotation(어노테이션명)") 특정 어노테이션에 대해서도 처리가능
    @Pointcut("execution(* *.messages..*.*(..))")
    void pointCutMethod() {}
    
    // 어드바이스
    @Around("pointCutMethod()")
    Object checkSecurity(ProceedingJoinPoint jp) { // 조인 포인트
        System.out.println("메소드 실행전");
        Object result = jp.proceed();
        System.out.println("메소드 실행후");
        return result;
    }

}
```

|번호|필드|메소드|RTW(Runtime Weaving)|CTW(Compile-Time Weaving|LWT(Load-Time Weaving)
|---|:---:|---|---|---|---|
|AspectJ|O|O|X|O|O|
|Spring AOP|X|O|O|X|X|


> ###런타임 위빙은 :

스프링 aop 에서 사용, proxy 로 생성하여 실제 타킷의 오브젝트 변형없이 실행

> ###컴파일 위빙 : 

AspectJ 에서 사용, 컴파일 과정에서 바이트 코드를 조작하여 Advisor 코드를 직접 삽입 (위빙중 가장 빠름) - lombok 과 같은 플러그인이 있는 경우 충돌 발생

> ###로드 위빙 : 

ClassLoader 를 이용하여 클래스가 JVM 에 로드 될 때, 바이트 코드를 조작을 통해 위빙 
RTW 처럼 소스 파일과 클래스파일의 조작을 가하지 않아 컴파일 시간 CTW 보다 빠름
메모리가 올라가는 과정에 진행되기 때문에 런타임 시에는 CTW 보다는 느린편이다. 
Application Context 에 객체가 로드 될 때 aspectj-weaver와 spring-instrument에
의한 객체 handling 이 발생하기 때문에 performace 가 저하된다

[참고]  
https://jaehun2841.github.io/2018/07/22/2018-07-22-spring-aop4/#aspectj%EB%9E%80
https://blog.naver.com/tmondev/220564638014
https://logical-code.tistory.com/118
https://atoz-develop.tistory.com/entry/AspectJ-Weaver%EB%A5%BC-%EC%82%AC%EC%9A%A9%ED%95%9C-%EC%95%A0%EB%85%B8%ED%85%8C%EC%9D%B4%EC%85%98-%EA%B8%B0%EB%B0%98%EC%9D%98-%EC%8A%A4%ED%94%84%EB%A7%81-AOP-%EA%B5%AC%ED%98%84-%EB%B0%A9%EB%B2%95