package com.geely.design.pattern.Observable;

/**
 * Created by xiaolin on 2019/1/2.
 */
public class Test {

    //观察者模式，被观察者中存有N个观察者，当发现变化的时候，会告诉每一个观察者
    //
    public static void main(String[] args) {
        //来了一个小丑
        Clown clown = new Clown();
        //观众入场了
        for (int i = 0; i < 20; i++) {
            Viewer v = new Viewer(i);
            clown.addObserver(v);
            System.out.println("座号为" + v.getSeatNo() + "的观众入座");
        }
        //小丑开始表演
        clown.perform();
        //小丑表演完毕，退场
        clown.exit();
    }

}
