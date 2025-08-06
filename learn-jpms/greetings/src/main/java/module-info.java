/**
 * 问候服务 API 模块定义
 * 职责：定义问候服务接口和实现
 *
 * @author hongliang.yin/Heneyin
 * @since 2025/8/5
 */
module com.heneyin.learn.jpms.greetings {
    // 导出公共 API 包，其他模块可以访问这个包中的公共类
    exports com.heneyin.learn.jpms.greetings;

    // 提供 GreetingService 实现
    // 使用服务提供机制，将 EnglishGreetingImpl 注册为 GreetingService 的实现。
    provides com.heneyin.learn.jpms.greetings.GreetingService
            with com.heneyin.learn.jpms.greetings.internal.EnglishGreetingImpl;

    // 注意！internal包没有导出，对模块外部不可见
}