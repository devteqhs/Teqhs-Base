package me.devteqhs.example.api.module;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @ Interface to set module info
 *
 * @author teqhs
 * @since 15/11/2022
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModuleInfo {

    /* Module Info */
    String name();
    int key() default 0;
    Category category();

}
