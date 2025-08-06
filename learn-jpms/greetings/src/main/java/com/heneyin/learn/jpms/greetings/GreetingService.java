package com.heneyin.learn.jpms.greetings;

/**
 * 问候服务接口
 * 定义标准的问候服务契约，所有实现类都应遵守此契约
 * @author hongliang.yin/Heneyin
 * @date 2025/8/5
 */
public interface GreetingService {
    /**
     * 生成问候语
     * @param name 被问候者的名字
     * @return 完整的问候语
     */
    String greet(String name);

    /**
     * 获取服务提供者的名称
     * @return 提供者名称
     */
    String getProviderName();
}
