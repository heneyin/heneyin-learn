package com.henvealf.learn.designpattern.mediator;

/**
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class MediatorMain {
    public static void main(String[] args) {
        Mediator mediator = new Mediator();

        Member memberA = new Member();
        Member memberB = new Member();

        mediator.registeMember(1, memberA);
        mediator.registeMember(2, memberB);

        String a = memberA.sayHi(2, "hi");
        String b =memberA.sayHi(2, "sdfsdf");

        System.out.println("a " + a);
        System.out.println("b " + b);
    }
}
