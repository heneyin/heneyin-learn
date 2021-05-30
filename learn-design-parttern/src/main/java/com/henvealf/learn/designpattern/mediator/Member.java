package com.henvealf.learn.designpattern.mediator;

/**
 * 需要互相交流的成员。
 * @author hongliang.yin/Henvealf on 2019/1/17
 */
public class Member {

    private Mediator mediator;

    public Member() {
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    public String sayHi(int number, String hello) {
        return mediator.say(number, hello);
    }

    public String backHi(String sendMessage) {
        if ("hi".equals(sendMessage)) {
            return "hi too";
        }
        return "who are you?";
    }


}
