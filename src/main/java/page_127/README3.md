# @Annotation

```java
public @interface MyAnnotation {
    /* enum 타입을 선언할 수 있습니다. */
    public enum Quality {BAD, GOOD, VERYGOOD}
    /* String은 기본 자료형은 아니지만 사용 가능합니다. */
    String value();
    /* 배열 형태로도 사용할 수 있습니다. */
    int[] values();
    /* enum 형태를 사용하는 방법입니다. */
    Quality quality() default Quality.GOOD;
}
```
----
### @Target 이란
어노테이션이 사용될 수 있는 타켓위치를 말한다
메소드면 메소드 위에만 해당 어노테이션을 작성할 수 있으며, 클래스 또는 필드에는 작성할 수 없다

```java
@Target(ElementType.METHOD)
public @interface SecurityCheck {
}
```

|타입|위치|
|---|---|
|ElementType.ANNOTATION_TYPE|어노테이션 앞|
|ElementType.CONSTRUCTOR|생성자 앞|
|ElementType.FIELD|필드 앞|
|ElementType.LOCAL_VARIABLE|변수 앞|
|ElementType.METHOD|메서드 앞|
|ElementType.PACKAGE|패키지 앞|
|ElementType.PARAMETER|파라미터 앞|
|ElementType.TYPE|클래스, 인터페이스, enum|

---
>###ANNOTATION_TYPE 
- 다른 어노테이션 클래스에 사용
```
@YourAnnotation
public @interface AnotherAnnotation {..}
```

>###PACKAGE
- 패키지 앞에 사용 (조건을 모름..)
```
@YourAnnotation
package org.yourcompany.somepackage;
```

>###TYPE
- 클래스, 인터페이스, 필드, 어노테이션 .. 등 앞에 사용
```
@YourAnnotation
public class SomeClass {..}
```

>###FIELD
- 필드 앞에 사용
```
@YourAnnotation
private String someField;
```

>###CONSTRUCTOR
- 생성자 앞에 사용
```
public class SomeClass {
    @YourAnnotation
    public SomeClass() {..}
}
```

>###METHOD
- 메서드 앞에 선언
```
@YourAnnotation
public void someMethod() {..}
```

>###PARAMETER
- 매개변수 앞에 사용
```
public void someMethod(@YourAnnotation param) {..}
```

---
### @Retention 이란?
얼마나 오랫동안 어노테이션 정보가 유지되는 지 설정할 수 있다
```java
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityCheck {
}
```
|타입|위치|
|---|---|
|RetentionPolicy.SOURCE|어노테이션 정보가 컴파일 시 사라짐, 바이트코드에 존재하지않음 (@Override)|
|RetentionPolicy.CLASS|클래스 파일에 존재하고 컴파일러에 의해 사용가능, 가상머신(런타임)에서는 사라짐|
|RetentionPolicy.RUNTIME|실행시 어노테이션 저보가 가상 머신에 의해서 참조 가능. 자바 리플렉션에 의해 사용|

---
### @Inherited 란?
슈퍼클래스를 상속한 서브클래스에서도 해당 어노테이션을 갖는다

```
@Inherited
public @interface MyAnnotation {

} 

@MyAnnotation
public class MySuperClass { ... } 

public class MySubClass extends MySuperClass { ... } 
```
MySubClass 에는 @MyAnnotation 을 작성하지 않아도 상속받았기 때문에 적용된다

---
### @Documented 란?
어노테이션이 지정된 대상의 JavaDoc 에 이 어노테이션의 존재를 표기하도록 지정

---
### 어노테이션에 값 넣기
```java
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface SecurityCheck {

    String value() default "";

}

class Tests{

    @SecurityCheck(value = "hello world")
    private String fieldName;

    public static void main(String[] args) throws InstantiationException, IllegalAccessException {

        MyContextContainer demo = new MyContextContainer();

        Tests test = demo.get(Tests.class);

        System.out.println(test.fieldName);
    }

    private static void testMethod() {
        System.out.println();
    }
}

class MyContextContainer {

    public MyContextContainer(){}

    /**
     * 매개변수로 받은 클래스의 객체를 반환합니다.
     * @param clazz
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T get(Class clazz) throws IllegalAccessException, InstantiationException {
        T instance = (T) clazz.newInstance();
        instance = invokeAnnonations(instance);
        return instance;
    }

    /**
     * 객체를 반환하기 전 어노테이션을 적용합니다.
     * @param instance
     * @param <T>
     * @return
     * @throws IllegalAccessException
     */
    private <T> T invokeAnnonations(T instance) throws IllegalAccessException {
        Field[] fields = instance.getClass().getDeclaredFields();
        for( Field field : fields ){
            SecurityCheck annotation = field.getAnnotation(SecurityCheck.class);
            if( annotation != null && field.getType() == String.class ){
                field.setAccessible(true);
                field.set(instance, annotation.value());
            }
        }
        return instance;
    }
}
```
---
### 참고
https://colinch4.github.io/2020-04-09/java-annotation/
https://jdm.kr/blog/216

[README - EntityScan 과 ComponentScan ](./README.md)  
[README2 - Spring AOP ](./README2.md)  