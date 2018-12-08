package com.geely.design.java8;

import lombok.Data;

import java.text.DecimalFormat;
import java.util.*;

import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xiaolin on 2018/12/8.
 */
public class JAVA {

    public static void main(String[] args) {

/*      Streams
        Streams的思想很简单，就是遍历。
        一个流的生命周期分为三个阶段：
        生成
        操作、变换（可以多次）
        消耗（只有一次）            */

//todo  --生成
        // 1. 对象
        Stream stream = Stream.of("a", "b", "c");
        // 2. 数组
        String[] strArray = new String[]{"a", "b", "c"};
        stream = Stream.of(strArray);
        stream = Arrays.stream(strArray);
        // 3. 集合
        List<String> list = Arrays.asList(strArray);
        stream = list.stream();

//      生成DoubleSteam、IntSteram或LongStream对象（这是目前支持的三个数值类型Stream对象）
        IntStream a = IntStream.of(new int[]{1, 2, 3}); // 根据数组生成
        IntStream b = IntStream.range(1, 3); // 按照范围生成，不包括3
        IntStream c = IntStream.rangeClosed(1, 3); // 按照范围生成，包括3


//   todo     --变换
//        一个流可以经过多次的变换，变换的结果仍然是一个流。
//        常见的变换：map (mapToInt, flatMap 等)、 filter、 distinct、
//        sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered


//   todo     --消耗
//        一个流对应一个消耗操作。
//        常见的消耗操作：forEach、 forEachOrdered、 toArray、 reduce、 collect、
//        min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator


        List<Student> students = Arrays.asList(
                new Student("Fndroid", 22, Student.Sax.MALE, 180),
                new Student("Jack", 20, Student.Sax.MALE, 170),
                new Student("Liliy", 18, Student.Sax.FEMALE, 160)
        );

        //java7
        for (Student student : students) {
            if (student.getSax() == Student.Sax.MALE) {
                System.out.println(student);
            }
        }
        //java8
        students.stream() // 打开流
                .filter(student -> student.getSax() == Student.Sax.MALE) // 进行过滤
                .forEach(System.out::println); // 输出

        //2.求出所有学生的平均年龄：
        OptionalDouble averageAge = students.stream()
                .mapToInt(Student::getAge) // 将对象映射为整型
                .average(); // 根据整形数据求平均值
        System.out.println("所有学生的平均年龄为：" + averageAge.orElse(0));

        /**
         * 可以看到这里的average方法得到一个OptionalDouble类型的值，这也是Java8的新增特性，
         * OptionalXXX类用于减少空指针异常带来的崩溃，可以通过orElse方法获得其值，如果值为null，则取默认值0。
         */

//        3.输出每个学生姓名的大写形式：
        List<String> names = students.stream()
                .map(Student::getName) // 将Student对象映射为String（姓名）
                .map(String::toUpperCase) // 将姓名转为小写
                .collect(Collectors.toList()); // 生成列表
        System.out.println("所有学生姓名的大写为：" + names);

//        4.按照年龄从小到大排序：
        List<Student> sortedStudents = students.stream()
                .sorted((o1, o2) -> o1.getAge() - o2.getAge()) // 按照年龄排序
                .collect(Collectors.toList()); // 生成列表
        System.out.println("按年龄排序后列表为：" + sortedStudents);

//        5.判断是否存在名为Fndroid的学生：
        boolean isContain = students.stream()
                .anyMatch(student -> student.getName().equals("Fndroid")); // 查询任意匹配项是否存在
        System.out.println("是否包含姓名为Fndroid的学生：" + isContain);

//        6.将所有学生按照性别分组：
        Map<Student.Sax, List<Student>> groupBySax = students.stream()
                .collect(Collectors.groupingBy(Student::getSax)); // 根据性别进行分组
        System.out.println(groupBySax.get(Student.Sax.FEMALE));

//        7.求出每个学生身高比例：
        double sumHeight = students.stream().mapToInt(Student::getHeight).sum(); // 求出身高总和
        DecimalFormat formator = new DecimalFormat("##.00"); // 保留两位小数
        List<String> percentages = students.stream()
                .mapToInt(Student::getHeight) // 将Student对象映射为身高整型值
                .mapToDouble(value -> value / sumHeight * 100) // 求出比例
                .mapToObj(per -> formator.format(per) + "%") // 组装为字符串
                .collect(Collectors.toList());
        System.out.println("所有学生身高比例：" + percentages);

    }


}
