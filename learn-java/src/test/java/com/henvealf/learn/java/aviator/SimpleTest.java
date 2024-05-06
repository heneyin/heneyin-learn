package com.henvealf.learn.java.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import org.junit.Test;

public class SimpleTest {

    @Test
    public void print() {
        Expression script = AviatorEvaluator.getInstance().compile("println('Hello, AviatorScript!');");
        script.execute();
    }

    /**
     * 带参数
     */
    @Test
    public void testParams() {
        String expression = "a-(b-c) > 100";
        Expression compiledExp = AviatorEvaluator.compile(expression);
        // Execute with injected variables.
        Boolean result =
                (Boolean) compiledExp.execute(compiledExp.newEnv("a", 100.3, "b", 45, "c", -199.100));
        System.out.println(result);
    }

    @Test
    public void validateExp() {
        try {
            AviatorEvaluator.validate("1 +* 2");
        } catch (Exception e) {
            e.printStackTrace();
            assert true;
            return;
        }
        assert false;
    }
}
