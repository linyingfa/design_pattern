package com.geely.design.java8;

import lombok.Data;

import javax.swing.text.View;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;


/**
 * Created by xiaolin on 2018/12/8.
 */
public class Lambda {
    public static void main(String[] args) {
        Lambda lambda = new Lambda();
        lambda.testLambda();
    }


    public void setOnClicListener1(String name, OnClickListenr OnClickListenr) {
        //做一些逻辑处理
    }

    public void setOnClicListener2(String name, OnClickListenr2 OnClickListenr) {
        //做一些逻辑处理
    }

    public void setOnClicListener3(String name, OnClickListenr3 OnClickListenr) {
        //做一些逻辑处理
    }

    public void setOnAA(String name, AAAA aaaa) {
        //做一些逻辑处理
    }

    public final static String ATG = "530";

    public void testLambda() {

        //第一种，传统匿名传入接口方式
        setOnClicListener1(ATG, new OnClickListenr() {
            @Override
            public void onClick(View view, int pos) {

            }
        });


        //第二种，使用Lambda表达式来代替:TODO 匿名接口方法

        //1.声明式,不使用大括号，只可以写单条语句
        //(parameters) -> expression
        //new 名字()； ---->就是一个对象，现在可以通过匿名，省去new关键字和覆盖的方法名,直接写上参数名字
        setOnClicListener1(ATG, ((view, pos) -> System.out.println("不使用大括号，只可以写单条语句")));


        //2.不声明式，使用大括号，可以写多条语句
        //(parameters) ->{ statements; }
        setOnClicListener1(ATG, (view, pos) -> {
            System.out.println("使用大括号，可以写多条语句");
            System.out.println("打印1");
            System.out.println("打印2");
        });


        //第三种，使用Lambda表达式调用类的静态方法
        setOnClicListener1(ATG, (view, pos) -> {//todo 这是回调才会执行的
            System.out.println("使用大括号，可以写多条语句");
            System.out.println("打印1");
            System.out.println("打印2");
            People.set();
        });


        //第四种，使用Lambda表达式调用类的实例方法
        setOnClicListener1(ATG, (view, pos) -> {
            new People().get();
        });


        //如果一个接口有多个方法则不能用lambda 简写
        setOnClicListener2(ATG, new OnClickListenr2() {
            @Override
            public void onClick(View view, int pos) {

            }

            @Override
            public int call(View view, int pos) {
                return 0;
            }
        });

        //如果只有一个表达式，则可以省略大括号和return关键字，
        // 编译器会自动的返回值；相对的，在使用大括号的情况下，则必须指明返回值。
        setOnClicListener3(ATG, ((view, pos) -> 1));


        //抽象类不能使用，
        //Target type of a lambda conversion must be an interface
        //lambda转换的目标类型必须是接口
        setOnAA(ATG, new AAAA() {
            @Override
            void aVoid(View view) {

            }
        });

    }

    @Data
    public static class People {
        private int age;
        private String name;

        public People() {
        }

        public static void set() {

        }

        public void get() {

        }

    }

    public interface OnClickListenr3 {
        int call(View view, int pos);
    }

    public interface OnClickListenr2 {
        void onClick(View view, int pos);

        int call(View view, int pos);
    }

    public interface OnClickListenr {
        void onClick(View view, int pos);
    }

    public abstract class AAAA {
        abstract void aVoid(View view);
    }


    public void testMethodReference() {

        //第一种，引用类的静态方法

        Car.create(new Supplier<Car>() {
            @Override
            public Car get() {
                return new Car();
            }
        });

        //--TODO 构造方法引用

        //Car类存在一个不带参数的构造方法，所以编译器不需要根据参数列表猜测构造方法的参数
        Car.create(() -> new Car());
        //更简单的写法就是这样了
        Car.create(Car::new);


        //--TODO 静态方法引用  （反正是引用类的静态方法都可以使用  ::）

        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);
        //Java8中给Iterable接口添加了forEach方法方便我们遍历集合类型
        cars.forEach(new Consumer<Car>() {
            @Override
            public void accept(Car car) {
                Car.collide(car);
            }
        });

        cars.forEach(c -> Car.collide(c));

        //Car::collide; 但是要建立在Lambda表达式之上
        //静态直接使用方法引用：
        cars.forEach(Car::collide);


        //第二种，引用类的实例方法 (内部的引用类)
        cars.forEach(A -> A.repair());
        //这里参数只有一个，而repair方法没有入参，所以不存在歧义，即可以改写为对应的方法引用：
        cars.forEach(Car::repair);


        //对象方法引用
        final Car police = Car.create(Car::new);
        cars.forEach((car1) -> police.follow(car1));
        cars.forEach(police::follow);
    }


    public void setOnClickListenr4() {

    }

    public interface OnClickListenr4 {
        void call(People people);
    }

}
