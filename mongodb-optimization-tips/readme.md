# Testing Spring Boot Repository Using MongoDB Testcontainers

Testcontainers are lightweight, throwaway container instances, used for common test cases like testing against a database, ActiveMQ or anything else that can run in a Docker container.

Testcontainers allows you to run your tests against a real instance of your application's dependencies, such as a real database, rather than against a mock or an in-memory database. We can configure the Testcontainers to closely mimic the production environment by using the specific versions of your dependencies and pre-loading with test data.

It spins up a new container instance for each test, hence every test is completely isolated from each other.

For running tests using the Testcontainers, we need to have the docker up and running, no other dependencies are required.

Read [the full article here](https://stacktips.com/articles/testing-spring-boot-repository-using-mongodb-testcontainers)

### Related topics:

* [#spring-boot](https://stacktips.com/topics/spring-boot)
* [#android](https://stacktips.com/topics/android)
* [#java](https://stacktips.com/topics/java)
* [#python](https://stacktips.com/topics/python)
* [#spring](https://stacktips.com/topics/spring)
* [#design-pattern](https://stacktips.com/topics/design-pattern)
* [#git](https://stacktips.com/topics/git)
* [#maven](https://stacktips.com/topics/maven)
