package com.balala.bootstrap.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *     author : 刘辉良
 *     e-mail : belongs_mymusic@163.com
 *     time   : 2019/11/20
 *     desc   : APP相关注解
 *     version: 1.0
 * </pre>
 */

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface BootStrapApp {


    /**
     * 默认名称
     *
     * @return 相关名称
     */
    String name() default "";

    /**
     * 组信息
     *
     * @return 配置组信息
     */
    String group() default "application";


    /**
     * 初始化顺序，优先级越高，越早初始化
     *
     * @return 优先级
     */
    int priority() default -1;


    /***
     * 是否在主线程中运行
     * @return 默认运行在主线程中
     */
    boolean isMainThread() default true;

}
