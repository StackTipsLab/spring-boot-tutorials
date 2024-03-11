# Add Context Path to a Spring Boot Application

In a Spring Boot application, the context path represents the base URL for accessing your application. By default, it's set to '/',

To change the context path, you need to add the following property to your application.properties file.

```
server.servlet.context-path=/api/1.0
```

Alternatively, if you're using the application.yaml file, you can do this

```
server:
   servlet:
     context-path: '/api/1.0'

```

Read [full article here](https://stacktips.com/articles/add-context-path-to-a-spring-boot-application)

### Related topics:

* [#spring-boot](https://stacktips.com/topics/spring-boot)
* [#android](https://stacktips.com/topics/android)
* [#java](https://stacktips.com/topics/java)
* [#python](https://stacktips.com/topics/python)
* [#spring](https://stacktips.com/topics/spring)
* [#design-pattern](https://stacktips.com/topics/design-pattern)
* [#git](https://stacktips.com/topics/git)
* [#maven](https://stacktips.com/topics/maven)
