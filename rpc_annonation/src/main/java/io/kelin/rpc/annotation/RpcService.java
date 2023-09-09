package io.kelin.rpc.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * klin
 * 2023/7/17 21:40
 * 服务提供者
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcService {

    /**
     * 接口的class
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 接口的className
     * @return
     */
    String interfaceClassName() default "";

    /**
     * 接口的版本
     */
    String version() default "1.0.0";

    /**
     * 服务分组，默认为空
     * @return
     */
    String group() default "";
}
