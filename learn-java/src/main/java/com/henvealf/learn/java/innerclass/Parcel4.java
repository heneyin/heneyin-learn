package com.henvealf.learn.java.innerclass;

/**
 * 使用接口来完全隐藏内部类的实现细节
 * Created by Henvealf on 2016/9/7.
 */
public class Parcel4 {
    private class PContents implements Contents {
        private int i = 11;
        @Override
        public String value() {
            return Integer.toString(i);
        }
    }

    protected class PDestination implements Destination {

        @Override
        public String readLine() {
            return "123";
        }
    }

    public Destination destination(){
        return new PDestination();
    }

    public Contents contents () {
        return new PContents();
    }
}
