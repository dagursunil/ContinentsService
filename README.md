# continent-service

Continent Flag Service is a Spring Boot project. It is used to get flags for the countries based on input paramenters. Spring Framework is employed for this mini project as it provides the extension configuration 
on convention mode reducing the need to write a lot of configuration and boilerplate code.

# production ready dependencies
1. Basic health-check and monitoring functions using spring HealthIndicator ,This module has some of the great features like metrics, health check, log, info, etc. exposed as the endpoints. 
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-actuator</artifactId>
</dependency>

```
2. Logger configuration.
3. To create a ‘fully executable’ jar with Maven use the following plugin configuration:
```
<plugin>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-maven-plugin</artifactId>
    <configuration>
        <executable>true</executable>
    </configuration>
</plugin>
```
4. Adjusting the number of concurrent threads in application.properties example: server.tomcat.max-threads=400 .

5. Known Issues :
 a.) H2 db is being used however no db operations are performed.
 b.) Values are being fetched from json file itself which has been added to project.
 c.) As flags are being used as string , Spring service results them as String only.
 4.) For showing flags as image application can be extended and Front End UI can be added.
