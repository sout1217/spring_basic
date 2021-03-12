### JDBC (Java Database Connectivity)
**JDBC API** 는 관계형 데이터베이스에 저장된 데이터에 접근하는 방법을 정의한다

*JDBC 드라이버*는 특정 데이터베이스에 대한 **JDBC API**의 구현체 이다

예를 들어 com.mysql.jdbc.Driver 는 MySQL 데이터베이스에 대한 드라이버 클래스 이름이고

org.hsqldb.jdbcDriver 는 순수 자바로 작성된 관계형 데이터베이스인 HSQLDB 에 대한 드라이버 클래스 이름이다

<u>스프링 JDBC</u> 는 JDBC API 위에서 데이테베이스와 더 쉽게 상호 작용할 수 있도록 스프링에서 제공하는 추상화 계층이다

JPA(Java Persistence API) 는 <u>자바 객체의 영속성</u>을 위한 자바의 표준화된 접근 방식을 정의한다

이 방식은 객체 지향 모델과 관계형 데이터베이스에 저장된 데이터 사이의 간격을 매우기 위해 객체 관계형 매핑(ORM, Object-relatinal mapping) 메커니즘을 사용한다

<u>영속성을 처리하기 위해 하이버네이트 ORM 을 사용하며, 쉽게 말을 하기위해 하이버네이트 ORM 을 그냥 하이버네이트 라고 부른다</u>


|명칭|내용|
|---|---|
|JDBC API|관계형 DB 접근 하는 방법 정의|
|JDBC Driver| JDBC API 의 구현체 - mysql, oracle, ...|
|Spring JDBC|JDBC API 위에서 데이터베이스와 더 쉽게 상호 작용하는 추상클래스|
|JPA|하이버네이트 ORM 을 이용하여 객체 지향 모델과 관계형 데이터베이스에 저장된 데이터 사이의 간격을 메우기 위한 객체 관계형 매핑이다|

### JDBC 와 JPA 의 차이
JDBC 와 JPA 는 서로 다른 문제를 해결하는 두 가지 API 세트이다 
- JDBC API 는 데이터베이스와의 상화 작용을 해결하지만
- JPA 는 객체지향방식으로 데이터베이스에 객체를 저장하고 가져오는 방법을 담당한다
    - JPA 구현체는 데이터베이스에 접근하기 위해 JDBC 드라이버에 의존한다
    


1. JDBC 드라이버 사용하기

2. 스프링 JDBC 사용하기

3. 하이버네이트 사용하기


### 스프링 JDBC 와 하이버네이트 장점
JDBC 와 달리 불필요하게 중복되는 코드를 절약하고 애플리케이션 자체의 로직을 구현하는데 집중 할 수 있도록 도와준다

---

## JDBC 드라이버
스프링 부트 애플리케이션에서 JDBC 드라이버로 데이터베이스에 직접 연결하려면 pom.xml 파일에 다음 의존성을 추가해야 한다

```mxml
<dependencis>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-jdbc</artifactId>
    </dependency>
    <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
    </dependency>
</dependencis>
```

`spring-boot-starter-jdbc` 모듈을 사용하면 스프링 부트는 `javax.sql.DataSource` 의 인스턴스를 기동하고
그것을 스프링 컨테이너에서 빈으로 사용할 수 있게 해준다 또한 데이터베이스 커넥션 풀을 설정한다

my-sql-connector-java 라이브러리는 MySql 데이터베이스에 대한 JDBC 드라이버이다 또한 일반적으로 필요한 mysql-connector-java 의 <version> 을 지정하지 않을 것 을 볼 수 있따

스프링이 DataSource 를 인스턴스화 하는 데 필요한 매개변수 설정

```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/messages?useSSL=false
    username: root
    password: root0327
    driver-class-name: com.mysql.jdbc.Driver
```
username, password, dirver-class 는 최소한의 설정이다
연결에서 UTF-8 인코딩을 사용해야 한다면 URL을 `jdbc:mysql://localhost:3306/messages?useUnicode=true&characterEncoding=UTF8` 로 변경하면 된다

```yaml
jdbc:mysql://localhost:3306/heydaze?useSSL=false&serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=UTF8
```
jdbc 설정이 끝났으면 이 다음은 MessageRepository 클래스를 변경해주어야 한다
DataSource 에서 데이터베이스 연결을 얻을 수 있도록 다음과 같이 MessageRepository 에 DataSource 인스턴스의 주입을 스프링에 요청해야 한다

[MessageRepositoryUsingJDBC.java](./app/messages/MessageRepositoryUsingJDBC.java)

Authentication plugin 'caching_sha2_password'.  mysql 에러
https://seoulbliss.tistory.com/88


## 스프링 JDBC
스프링 JDBC 는 JDBC API 위의 추상화 계층을 제공한다

JdbcTemplate 클래스는 이 계층에서의 핵심이다. 

연결을 관리하고 JDBC API와 상호작용하는 워크플로를 제공하는 데 도움을 준다

쿼리문을 준비하고 쿼리 결과를 처리할 방법만 지정하면 된다.

NamedParameterJdbcTemplate 클래스는 JdbcTemplate 객체를 래핑한 클래스로 JDBC 의 "?" 플레이스 홀더 대신에 이름을 지정한 매개변수를 사용할 수 있는 기능을 제공한다
```
    [jdbc 방식]
    String insertSql = "insert into messages(`id`, `text`, `created_date`) value (null, ?, ?)";
```

```
    [NameParameterJdbcTemplate class 방식]
    String insertSql = "insert into messages(`id`, `text`, `created_date`) value(null, :text, :createdDate)";
  
```

또한 스프링 JDBC는 데이터베이스에서 제공하는 메타 데이터로 JDBC 작업을 단순화하기 위해 `SimpleJdbcInsert` 와 `SimpleJdbcCall`을 제공한다

메타 데이터는 DatabaseMetaData 인스턴스를 반환하는 connection.getMetaData() 메소드를 호출해 가져온다 

connection.getMetaData() 에는 데이터베이스에 정의된 테이블과 각 테이블이 가진 컬럼과 같은 정보를 포함한다

`SimpleJdbcInsert` 와 `SimpleJdbcCall` 을 사용한다면 메타 데이터에 대한 걱정할 필요가 없다 -> 스프링에 있는 그 2개가 메타데이터를 관리한다

스프링 JDBC 에서는 JDBC 작업을 자바 객체로 표현하는 방법도 제공한다 `MappingSqlQuery` 객체를 생성해 데이터베이스 쿼리를 실행하고, `SqlUpdate` 객체를 생성해 삽입/업데이트 작업을 수행 할 수있다

그리고 `StoredProceure` 객체를 생성해 데이터베이스에서 저장 프로시저(stored procedure) 를 호출 할 수 있다

이 객체들은 재사용 가능하며 스레드 세이프(thread safe)하다.

[MessageRepositoryUsingSpringJDBC.java](./app/messages/MessageRepositoryUsingSpringJDBC.java)

## 하이버네이트 (Hibernate)
#### 의존성 추가하기
```mxml
<dependencis>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-orm</artifactId>
    </dependency>
    <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-core</artifactId>
    </dependency>
</dependencis>
```

spring-orm 라이브러리는 하이버네이트와 같은 ORM 기술을 기반으로 하는 스프링의 ORM을 지원한다
hiberate-core 라이브러리는 하이버네이트 ORM 프레임워크이다

Message 객체와 messages 테이블의 레코드를 매핑하는 방법을 알 수 있도록 하이버네이트에 몇 가지 메타 데이터를 제공해야한다

Message 클래스는 이미 jdbc 에서 사용 중이기 떄문에

MessageEntity 라는 클래스를 만들어 메타 데이터를 추가할 수 있게 JPA 어노테이션을 사용한다 

[MessageEntity](./app/messages/MessageEntity.java)

ResponseEntity 를 사용하려면 해당 클래스에 getter 메소드가 있어야지만 반환을 해준다
때문에 클래스안에는 GETTER 메소드를 작성해주어야 하지만 조회할 때 모든 정보를 보낸다는 것은 좋은 선택이 아니다
그래서 response 할 때에는 따로 dto 클래스를 만들어 원하는 필드만 입력하여 모든 항목에 getter 를 만드는 것 이다

|어노테이션|설명|컬럼2|
|---|---|---|
|@Entity|Entity 클래스로 표시하여 Hibernate 에서 인식할 수 있게 한다|
|@Table|MessageEntity 클래스와 매핑되는 messages 테이블을 지정한다|
|@id|해당 필드를 기본키로 사용|
|@GeneratedValue|id값이 생성되는 방법을 지정한다 - Identity 전략은 auto_increment 와 동일하다|
|@Column|테이블의 해당 컬럼 속성을 설정|
|@Temporal|`java.util.Date`, `java.util.Calendar` 타입의 필드에 필수적으로 추가해야 한다 TIMESTAMP 값은 createdDate 필드를 jdbc 가 이해할 수 있는 java.sql.Timestamp 타입의 값과 매핑한다

하이버네이트를 사용하는 클래스, 즉 Entity 클래스는 필수적으로 디폴트 생성자가 필요하다

```
하이버네이트는 @Column 어노테이션의 nullable 설정과 length 속성을 기반으로 데이터 검증을 수행하지 않는다
hbm(Hibernate Mapping) 으로 부터 DDL(Date Definition Language)을 생성하는 hbm2ddl 에서 이 메타데이터를
사용한다 데이터베이스에서 데이터 구조를 생성/수정/삭제하기 위해 DDL 스크립트를 활용 할 수 있다 messages 테이블을
생성하는 데 앞에서 제공된 SQL 은 DDL 스크립트 이다. 반면 데이터 검증은 수행하려면 나중에 Bean Validation 을 이용하면 된다
```

하이버네이트에서 org.hibernate.Session 은 엔티티를 저장하고 불러오기 위한 주요 인터페이스이다

하이버네이트 SessionFactory 인스턴스에서 세션을 생성할 수 있다 스프링 ORM 으로 SessionFactory 의 인스턴스를
생성하려면 스프링 FactoryBean인 스프링의 LocalSessionFactoryBean 을 사용하면 된다

LocalSessionFactoryBean 을 정의하기 위해 AppConfig 설정에서 정의 해야한다

[AppConfig.java](./app/messages/AppConfig.java)




