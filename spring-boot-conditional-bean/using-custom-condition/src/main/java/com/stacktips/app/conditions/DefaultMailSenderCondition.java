package com.stacktips.app.conditions;


import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;
import org.springframework.stereotype.Component;

@Component
public class DefaultMailSenderCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        String mailSender = context.getEnvironment().getProperty("email.mail-sender");
        return null== mailSender || mailSender.trim().equals("default");
    }
}