Spring Template Engine
---
MVC 패턴에서 **C는 컨트롤러(Controller)**, **V 는 HTML 마크업이 위치하는 뷰(View)**다
그리고 M은 컨트롤러가 생성하고 HTTP 응답을 통해 클라이언트에 전송되어 뷰가 최종결과를 렌더링하는 데 사용할 수 있는 **데이터를 포함하는 모델(Model)**
이다 (마크업은 p태그, div태그 등 모든 태그들을 말한다)


View 에 Model 을 보내기 위해서는 템플릿 엔진이라는 것이 필요하다
그렇지 않으면 서버에 데이터를 전송할 수 없기 때문이다
Model 을 넘겨주기 위한 **JSP, thymeleaf, mustache, handlebars** 등 다양한 템플릿 엔진들이 있다

템플릿 엔진을 사용하기 위해 메이븐(Maven) 에 의존성을 추가해야 한다

```mxml
<dependencies>
    <!-- mustache 템플릿 엔진 -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-mustache</artifactId>
    </dependency>
    <!-- thymeleaf --> 
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-thymeleaf</artifactId>
    </dependency>
</dependencies>
```

mustache 가 다른 템플릿 엔진들에 비해 문법이 간단하다

Spring 에서는 기본 경로를 root/resources/static 과 root/resources/templates 잡아준다
model 을 넘겨주기 위해서는 templates 폴더 위치하고 있어야 한다

템플릿 엔진을 설치하면 spring 에서는 suffix 부분에 해당 확장자명으로 대체해준다

|엔진|확장자|
|---|---|
|thymleaf|.html|
|handlebars|.hbs|
|mustache|.mustache|

suffix 부분에 확장자를 자동으로 넣어주기 때문에 @Controller 메소드의 return 부분에 prefix 부분과 suffix 부분을 생략이 가능하다

템플릿 엔진은 꼭 확장자를 저렇게 사용하지 않아도 된다
application.yml 에서 `spring.mustache.suffix: .html` 로 바꾸면
.mustache 확장자를 사용하지 않고 .html 확장자로도 가능하다

[MustacheTemplateTests]: ./../../../test/java/page_108/app/messages/MessageControllerTests.java
[MustacheTemplateTests.java][MustacheTemplateTests]
