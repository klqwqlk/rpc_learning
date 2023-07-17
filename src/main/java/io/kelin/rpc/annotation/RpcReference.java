package io.kelin.rpc.annotation;

import org.springframework.beans.factory.annotation.Autowired;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * klin
 * 2023/7/17 21:40
 * 服务提供者
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Autowired
public @interface RpcReference {

    /**
     * 版本号
     * @return
     */
    String version() default "1.0.0";

    /**
     * 注册中心类型, 目前包含：zookeeper、nacos、etcd、consul
     */
    String registerType() default "zookeeper";

    /**
     * 注册中心地址
     * @return
     */
    String registryAddress() default "127.0.0.1:2181";

    /**
     * 负载均衡类型
     * @return
     */
    String loadBalanceType() default "zkconsistenthash";

    /**
     * 序列号与反序列化, 有protostuff、kryo、json、jdk、hessian2、fst
     * @return
     */
    String serializationType() default "protostuff";

    /**
     * 超时时间，默认5s
     */
    long timeout() default 5000;

    /**
     * 是否异步执行
     */
    boolean async() default false;

    /**
     * 是否单向调用
     */
    boolean oneway() default false;

    /**
     * 代理的类型，jdk：jdk代理， javassist: javassist代理, cglib: cglib代理
     */
    String proxy() default "jdk";

    /**
     * 服务分组，默认为空
     */
    String group() default "";

}
