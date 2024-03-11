# Testing Spring Boot REST API using Mockito and MockMvc

The best way to confirm the reliability and correctness of software applications is to write tests that assert the desired behaviour of an application. This post covers how to perform unit and integration testing in Spring Boot.

The spring-boot-starter-teststarter dependency is required for writing the unit and integration tests in the Spring Boot application. The Spring Boot version 3.2.2 spring-boot-starter-test includes the following transitive dependencies.

- Jupiter JUnit5: The de facto standard for unit testing Java.
- Jayway JsonPath: A popular Java library used for parsing and querying JSON documents. It is used for navigating through the structure of your JSON data and accessing specific values or elements.
- Awaitility: Used for waiting for asynchronous operations to complete without introducing additional logic in your code.
- Hamcrest: Hamcrest is used for writing expressive and readable assertions in unit tests. It provides a clear and concise way to express what you expect from your code, making your tests easier to understand and maintain.
- Mockito: Java mocking framework used to create mock objects that simulate the behaviour of real objects without actually implementing their functionality. This makes it easier to isolate the code you're testing from external dependencies and write more focused and reliable tests.

Read [the full article here](https://stacktips.com/courses/spring-boot-for-beginners/testing-spring-boot-using-mockito-and-mockmvc)

### Related topics:

* [#spring-boot](https://stacktips.com/topics/spring-boot)
* [#android](https://stacktips.com/topics/android)
* [#java](https://stacktips.com/topics/java)
* [#python](https://stacktips.com/topics/python)
* [#spring](https://stacktips.com/topics/spring)
* [#design-pattern](https://stacktips.com/topics/design-pattern)
* [#git](https://stacktips.com/topics/git)
* [#maven](https://stacktips.com/topics/maven)
