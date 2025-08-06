package com.heneyin.learn.jpms.app;

import com.heneyin.learn.jpms.greetings.GreetingService;

import java.util.ServiceLoader;

/**
 * @author hongliang.yin/Heneyin
 * @date 2025/8/5
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("===== JPMS 示例程序 ========");

        System.out.println("\n[方式1] 使用ServiceLoader加载服务:");
        ServiceLoader<GreetingService> loader = ServiceLoader.load(GreetingService.class);

        loader.stream()
                .map(ServiceLoader.Provider::get)
                .forEach(service -> {
                    System.out.println("服务提供者: " + service.getProviderName());
                    System.out.println("问候语: " + service.greet("Hello"));
                });

        // 方法2: 直接使用实现类(不推荐，仅用于演示)
        // 注意: 这会导致编译错误，因为EnglishGreetingImpl在未导出的internal包中
        // GreetingService service = new EnglishGreetingImpl();
    }
}
