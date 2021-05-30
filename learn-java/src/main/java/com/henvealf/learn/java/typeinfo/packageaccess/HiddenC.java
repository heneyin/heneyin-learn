package com.henvealf.learn.java.typeinfo.packageaccess;

import com.henvealf.learn.java.typeinfo.A;

/**
 * 在InterfaceViolation 中的问题，解决方法就是程序员自己注意着点，尽量不要这么使用。
 * 除此之外，在程序方面，可以进行一些控制，即使用宝访问权限来限制.
 * 在这个包里面，唯一对外开放的public既是HiddenC,它会产生A接口类型的对象，
 * 这样的话你就无法在包的外部调用A以外的任何方法了，因为你无法在包的外部找到C，就无法进行转型。
 * Created by Henvealf on 2016/9/10.
 */

class C implements A {

    @Override
    public void f() {
        System.out.println("public C.f()");
    }

    public void g() {
        System.out.println("public C.g()");
    }
    void u() {
        System.out.println("package C.u()");
    }
    protected void v() {
        System.out.println("protected C.v()");
    }

    public void w() {
        System.out.println("private C.w()");
    }
}
public class HiddenC {
    public static A makeA(){
        return new C();
    }
}
