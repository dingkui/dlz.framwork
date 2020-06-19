package com.dlz.framework.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;


public class InitPoolCondition implements Condition {
    @Override
    public boolean matches(ConditionContext conditionContext, AnnotatedTypeMetadata annotatedTypeMetadata) {
        String active = conditionContext.getEnvironment().getProperty("dlz.redis.initPool");
        return "true".equalsIgnoreCase(active);
    }
}