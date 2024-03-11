# Choosing from Multiple Bean Implementations in Spring Boot

If you have multiple implementations of the same interface, Spring needs to know which bean to inject into a class.

In this article, we will explore what are the different approaches to injecting beans conditionally when you have multiple implantation of a component or service.

- [Option-1: Using @Primary Bean Annotation](https://stacktips.com/articles/choose-from-multiple-bean-types-springboot#option-1-using-primary-bean-annotation)
- [Option-2: Autowiring using @Qualifier](https://stacktips.com/articles/choose-from-multiple-bean-types-springboot#option-2-autowiring-using-qualifier)
- [Option-3: Using ApplicationContext to Dynamically Select Beans](https://stacktips.com/articles/choose-from-multiple-bean-types-springboot#option-3-using-applicationcontext-to-dynamically-select-beans)
- [Option-4: Custom Annotation and Bean Factory](https://stacktips.com/articles/choose-from-multiple-bean-types-springboot#option-4-custom-annotation-and-bean-factory)
- [Option-5: Bean Selection Using Custom Conditions](https://stacktips.com/articles/choose-from-multiple-bean-types-springboot#option-5-bean-selection-using-custom-conditions)

### Related topics:

* [#spring-boot](https://stacktips.com/topics/spring-boot)
* [#android](https://stacktips.com/topics/android)
* [#java](https://stacktips.com/topics/java)
* [#python](https://stacktips.com/topics/python)
* [#spring](https://stacktips.com/topics/spring)
* [#design-pattern](https://stacktips.com/topics/design-pattern)
* [#git](https://stacktips.com/topics/git)
* [#maven](https://stacktips.com/topics/maven)
