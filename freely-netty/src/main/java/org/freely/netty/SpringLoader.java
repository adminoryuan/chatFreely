package org.freely.netty;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringLoader {
    private static AnnotationConfigApplicationContext  context;

    public static void Init()  {
        context = new AnnotationConfigApplicationContext();
        context.scan("org.freely.netty"); // 指定要扫描的包路径
        context.refresh();
    }

    public static <T> T getBean(Class<T> cls){
        return context.getBean(cls);
    }

}
