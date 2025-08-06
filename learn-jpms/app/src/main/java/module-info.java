/**
 * 主应用模块定义
 *
 * 模块名称: com.heneyin.learn.jpms.app
 * 职责: 演示如何使用JPMS模块系统
 *
 * @author hongliang.yin/Heneyin
 * @date 2025/8/5
 */
module com.heneyin.learn.jpms.app {
    // 依赖 greetings 模块
    requires com.heneyin.learn.jpms.greetings;

    // 声明使用 GreetingService 服务
    // 这使得我们可以使用 ServiceLoader 加载服务实现
    uses com.heneyin.learn.jpms.greetings.GreetingService;
}