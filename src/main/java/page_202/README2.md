# Solid Design Pattern

|약어|명칭 (en)|명칭 (kor)|
|:---:|:---|:---:|
|SRP|Single Responsibility Principle|단일 책임 원칙|
|OCP|The Open-Closed Principle|개방-폐쇄 원칙|
|LSP|The Liskov Substitution Principle|리스코프 치환 원칙|
|ISP|The Interface Segregation Principle|인터페이스 분리 원칙|
|DIP|The Dependency Inversion Responsibility Principle|의존 관계 역전 원칙|

<br>

##📌 단일 책임 원칙 (SRP)
> 한 클래스는 변경에 대한 이유를 하나만 가져야 한다

즉, 클래스를 변경할 때 클래스를 변경하기 위한 다른 이유를 생각할 수 있다면 해당 클래스는 SRP 를 위반하는 것이다.

비유를 하자면 로봇이라는 클래스가 있고, 팔 클래스가 있다면, <u>팔 클래스가 다리의 역할까지 하면 안된 다는 것이다.</u>

이 원칙은 주로 하위 수준의 설계에서 사용해야 한다 (= 제일 하위 클래스를 뜻함)


##📌  개방 폐쇄 원칙 (OCP)
> 소프트웨어 아티팩트(artifact)는 확장에 대해 열려 있어야 하고, 수정에 대해서는 닫혀 있어야 한다

상위 클래스의 메소드가 어떤 행위에 대해 포괄되는 의미여야 하고 포괄적으로 수행되어야 한다

비유를 하면, Shape 상위클래스와 Circle, Square 하위클래스 를 가지고 있다고 할 떄

Shape 에 draw 메소드를 호출 하면 Circle 과 Square 모두 그림을 그리는 기능을 수행하여 한다

<u>결론, 하위 클래스를 작성하는 데 상위 클래스를 수정해서는 안되는 것 이다. 

하위 클래스를 계속 추가하는 데(확장), 상위 클래스를 수정한다면 확장에 닫혀 있는 것 이다. <-> 확장에 열리있고 수정에 닫힘</u>
 

##📌 리스코프 치환의 원칙 (LSP)
> 서브타입은 그것의 기반 유형(base type) 으로 치환 가능해야 한다

정사각형 (Square) 는 직사각형 (Rectangle) 에 포함되는 것을 알고 아래 코드를 보라

```java
class Square extends Rectangle {
    private int width;
    private int height;
    
    public void setWidth(int width){
        this.width = width;
        this.height = width;
    }
    public void setHeight(int height) {
        this.width = height;
        this.height = height;        
    }
}

class App {
    public void resize(Rectangle rectangle){
        rectangle.width(5); 
        rectangle.height(10);
    }
}
```

위 코드를 보면 서브타입인 정사각형 Square 는 맞지만, 상위타입의 직사각형(Rectangle) 은 될 수 없다.

<u>서브타입이 상위타입으로 치환이 안될 때 리스코프 치환 원칙을 어긴 것이다.</u>

##📌 인터페이스 분리 원칙 (ISP)
> 클라이언트는 자신이 사용하지 않는 메소드에 의존하도록 강제되어서는 안된다.



##📌 의존성 제어 역전 원칙 (DIP)
> 상위 수준의 모듈을 하위 수준의 모듈에 의존해서는 안 된다. 둘 모두 추상화에 의존해야 한다  
> 추상화는 구체적인 사항에 의존해서는 안 된다. 구체적인 상황이 추상화에 의존해야 한다>


#### DIP 위반
```java
class Item {}

class JdbcRepository {
    public List<Item> find() {
        return Collections.emptyList();
    }
}
class CsvGenerator {
    public String generate(List<Item> items) {
        return "c:/item";
    }
}
class ZipCompressor {
    public String zip(String csvFilePath) {
        return "item.zip";
    }
}

public class Exporter {
    
    private JdbcRepository jdbcRepository;
    private CsvGenerator csvGenerator;
    private ZipCompressor zipCompressor;

    public Exporter(JdbcRepository jdbcRepository, CsvGenerator csvGenerator, ZipCompressor zipCompressor) {
        this.jdbcRepository = jdbcRepository;
        this.csvGenerator = csvGenerator;
        this.zipCompressor = zipCompressor;
    }

    public void export() {
        List<Item> items = jdbcRepository.find();
        
        if (items.isEmpty()) {
            return;
        }

        String csvFilePath = csvGenerator.generate(items);
        String compressed = zipCompressor.zip(csvFilePath);
    }
}

```

### DIP 해결

```java
class Item {}

interface Repository {
    List<Item> find();
}

interface Generator {
    String generate(List<Item> items);
}

interface Compressor {
    String zip(String path);
}

class JdbcRepository implements Repository {
    @Override
    public List<Item> find() {
        return Collections.emptyList();
    }
}
class CsvGenerator implements Generator {
    @Override
    public String generate(List<Item> items) {
        return "c:/item";
    }
}
class ZipCompressor implements Compressor {
    @Override
    public String zip(String csvFilePath) {
        return "item.zip";
    }
}

public class Exporter {

    private JdbcRepository jdbcRepository;
    private CsvGenerator csvGenerator;
    private ZipCompressor zipCompressor;

    public Exporter(JdbcRepository jdbcRepository, CsvGenerator csvGenerator, ZipCompressor zipCompressor) {
        this.jdbcRepository = jdbcRepository;
        this.csvGenerator = csvGenerator;
        this.zipCompressor = zipCompressor;
    }

    public void export() {
        List<Item> items = jdbcRepository.find();

        if (items.isEmpty()) {
            return;
        }
        String csvFilePath = csvGenerator.generate(items);
        String compressed = zipCompressor.zip(csvFilePath);
    }
}
```

클래스를 직접적으로 의존하지 않아 확장성이 자유로워졌다

MySQL, Oracle, MsSQL, MongoDB 확장이 가능하며
zip 파일 뿐만이나 agg, tar, 7z 으로도 확장하기가 쉬워진다 

[README - 코드 설계](./README2.md)