java Java 模块系统(JPMS) 的学习示例 

构建命令，根目录执行：
```
mvn clean install
```

执行程序 jar：
```
# 先设置 java home, 选择 java 17 + 的 sdk
export JAVA_HOME=/Library/Java/JavaVirtualMachines/temurin-21.jdk/Contents/Home
java --module-path "app/target/app-1.0-SNAPSHOT.jar:greetings/target/greetings-1.0-SNAPSHOT.jar" \
     --module com.heneyin.learn.jpms.app/com.heneyin.learn.jpms.app.Main
```

构建要点, 需要在模块 pom 中添加插件：
```
<!-- 公共构建配置 -->
    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-jar-plugin</artifactId>
                <version>3.3.0</version>
                <configuration>
                    <archive>
                        <!-- ！！确保模块信息被打包到JAR中 -->
                        <manifestEntries>
                            <Multi-Release>true</Multi-Release>
                        </manifestEntries>
                    </archive>
                </configuration>
            </plugin>
        </plugins>
    </build>
```

# 核心用途
### **JPMS 的核心用途**
1. **强封装性**
    - 模块必须显式声明导出的包（`exports`），未导出的包对外部完全隐藏。
    - 解决了传统 `public` 访问修饰符过度暴露实现细节的问题。

2. **显式依赖管理**
    - 模块需声明依赖的其他模块（`requires`）。
    - 避免类路径（Classpath）下的隐式依赖冲突。

3. **可靠配置**
    - 启动时验证模块依赖的完备性，避免运行时缺失依赖导致的 `NoClassDefFoundError`。

4. **优化 Java 平台**
    - 将 JDK 拆分为独立模块（如 `java.base`、`java.sql`）。
    - 允许应用仅依赖必要的模块，减少运行时开销。

5. **增强安全性**
    - 限制对内部 API（如 `sun.misc.Unsafe`）的反射访问。
# 解决的问题

1. jar 地域
传统 Java 应用中，当多个JAR文件包含同名类或不同版本库时，类路径(Classpath)机制无法解决冲突。这导致不可预测的NoSuchMethodError或ClassNotFoundException。
JPMS通过强制模块唯一命名和显式依赖声明（requires），确保运行时只加载指定版本的模块，彻底消除依赖冲突。
2. 弱封装性问题
Java原有的public访问修饰符允许任何代码访问类，导致实现细节意外暴露。开发者常被迫使用protected或默认可见性来限制访问，但这仍不够严格。
JPMS引入强封装：模块内未通过exports显式导出的包完全对外不可见，即使其中包含public类。这真正实现了"基于接口而非实现编程"的原则。

3. 脆弱的类路径问题
传统类路径在启动时不验证依赖完整性，缺失的JAR文件直到运行时调用相关代码才会抛出NoClassDefFoundError。
JPMS在启动阶段严格检查所有模块依赖（requires语句），若依赖链不完整立即报错，将"缺失依赖"这类运行时错误提前到启动时暴露。

4. 运行时臃肿问题
标准JRE包含整个Java平台（约300MB），即使应用仅使用基础功能。
JPMS将JDK拆分为约100个模块（如java.base核心模块仅60MB）。结合jlink工具，开发者可构建仅包含必需模块的最小化运行时镜像，典型云原生应用可缩减至40-60MB，极大提升部署效率。

5. 内部API滥用问题
长期以来，开发者通过反射访问sun.misc.*等非标准JDK内部API，导致应用在不同Java版本间兼容性差。
JPMS默认禁止访问未导出模块，包括大多数JDK内部API。虽然可通过--add-opens绕过，但显式操作促使开发者转向标准API。

6. 隐式依赖问题
类路径机制下，所有JAR的类都在全局命名空间可见，导致代码可能意外依赖未声明的库（如通过传递性依赖）。
JPMS要求每个模块显式声明所有依赖（requires），未声明的模块即使物理存在也无法访问，消除"隐藏依赖"带来的不确定性。
