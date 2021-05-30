package com.henvealf.learn.designpattern.mediator;

import java.util.HashMap;
import java.util.Map;

/**
 * 中介者模式
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class Mediator {

    private Map<Integer, Member> memberMap = new HashMap<>();

    public Mediator() {

    }

    public void registeMember(int number, Member member) {
        member.setMediator(this);
        memberMap.put(number, member);
    }

    public String say(int number, String hello) {
        Member member = memberMap.getOrDefault(number, null);
        if (member != null) {
            return member.backHi(hello);
        }
        throw new RuntimeException("member on " + number + " is not registe");
    }

}
