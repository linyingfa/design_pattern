package com.geely.design.java8;



/**
 * Created by xiaolin on 2018/12/8.
 */
public class Car {

    // 通过Supplier获取Car实例
    public static Car create(Supplier<Car> supplier) {
        return supplier.get();
    }

    // 静态方法，一个入参Car对象
    public static void collide(final Car car) {
        System.out.println("Collide " + car.toString());
    }

    // 一个入参Car
    public void follow(final Car car) {
        System.out.println("Following car " + car.toString());
    }

    // 不带入参
    public void repair() {
        System.out.println("Repaired car " + this.toString());
    }
}
