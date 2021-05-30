package com.henvealf.learn.java.janino;

import org.codehaus.janino.SimpleCompiler;
import org.junit.Test;

/**
 * @author hongliang.yin/Henvealf
 * @date 2021/4/15
 */
public class JaninoTest {

    @Test
    public void useComplieCodeTest() throws IllegalAccessException, InstantiationException, ClassNotFoundException {
        ClassLoader classLoader = this.getClass().getClassLoader();
        Class<Doo> objectClass = compileDooCode(classLoader);
        Doo doo = objectClass.newInstance();
        doo.doo();

        Class<?> classA = classLoader.loadClass("ClassA");
        System.out.println("a: " + classA.getCanonicalName());

        System.out.println(doo.getClass().getCanonicalName());
        System.out.println(JaninoTest.class.getCanonicalName());
        Class<Doo> usedDoo = useDooClassCodeTest(classLoader);
        Doo doo1 = usedDoo.newInstance();
        doo1.doo();
    }

    public <T> Class<T>  compileDooCode(ClassLoader classLoader) throws IllegalAccessException, InstantiationException {
        String code = "public class ClassA implements com.henvealf.learn.java.janino.Doo { public void doo() {System.out.println(\"in do\"); }  }";
        // 名称必须要与类名相同
        String name = "ClassA";
        return compile(classLoader, name, "com/henyin/", code);
    }

    private <T> Class<T> useDooClassCodeTest(ClassLoader classLoader) {
        String code = "public class UseDoo implements com.henvealf.learn.java.janino.Doo " +
                "{ public void doo() { ClassA a = new ClassA(); a.doo(); } }";
        String name = "UseDoo";
        return compile(classLoader, name, "com/henyin/", code);
    }

    /**
     * 测试使用事实编译后的代码
     */
    private <T> Class<T> compile(ClassLoader classLoader, String name, String fileName, String code) {

        SimpleCompiler compiler = new SimpleCompiler();
        compiler.setParentClassLoader(classLoader);
        try {
            compiler.cook(fileName, code);
        } catch (Throwable t) {
            throw new RuntimeException(t);
            //System.out.println(addLineNumber(code));
            //throw new InvalidProgramException(
             //       "Table program cannot be compiled. This is a bug. Please file an issue.", t);
        }
        try {
            //noinspection unchecked
            return (Class<T>) compiler.getClassLoader().loadClass(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Can not load class " + name, e);
        }
    }

}
