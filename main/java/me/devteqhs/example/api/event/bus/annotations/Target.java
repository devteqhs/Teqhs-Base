package me.devteqhs.example.api.event.bus.annotations;



import me.devteqhs.client.api.event.bus.Priorities;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


@Retention(RetentionPolicy.RUNTIME)
@java.lang.annotation.Target(ElementType.FIELD)
public @interface Target {
    byte value() default Priorities.MEDIUM;
}