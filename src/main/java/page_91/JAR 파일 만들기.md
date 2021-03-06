메이븐 JAR 빌드

```mxml
<build>
        <finalName>messages</finalName>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-assembly-plugin</artifactId>
                <executions>
                    <execution>
                        <phase>package</phase>
                        <goals>
                            <goal>single</goal>
                        </goals>
                        <configuration>
                            <archiveBaseDirectory>${project.basedir}</archiveBaseDirectory> <!-- target 디렉토리-->
                            <archive>
                                <manifest>
                                    <mainClass>page_91.app.messages.Application</mainClass> <!-- 메인 클래스 설정 -->
                                </manifest>
                            </archive>
                            <!-- spring-context-5.0.3 jar 파일을 포함시켜야 하기 때문에 아래구문을 사용한다 -->
                            <descriptorRefs>
                                <descriptorRef>jar-with-dependencies</descriptorRef> <!-- 실제 java -jar target/messages-jar-with-dependencies.jar 실행파일 -->
                            </descriptorRefs>
                        </configuration>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>
```
---
Maven 을 설치하여 환경변수 설정이 되어 있으면
프로젝트 디렉토리 이동하여 아래 코드를 치면 target 폴더에 jar 파일이 생성이 된다
```html
mvn install
```

그리고 cmd 명령어로 cd 를 이용하여 해당 프로젝트로 와서
```cmd
java -jar target/messages-jar-with-dependencies.jar 파일을 실행해준다 
```

인텔리제이 에서는 내장 maven 이 있기 때문에 간단하게 빌드하고 실행 시킬 수 있다
