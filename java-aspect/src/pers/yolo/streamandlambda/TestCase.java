package pers.yolo.streamandlambda;

import pers.yolo.streamandlambda.pojo.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCase {
    public static void main(String[] args) {
        RegisterStudent<Student> rs = Student::new;
        List<Student> students = Arrays.asList(
                rs.registerStudent("张三", 18, 180),
                rs.registerStudent("李四", 19, 181),
                rs.registerStudent("王五", 20, 182),
                rs.registerStudent("赵六", 21, 183)
        );
        // 注意：每个stream只用用1次
        // of
        List<Integer> list = Stream.of(1, 3, 5, 7, 9).collect(Collectors.toList());
        System.out.println(list);
        // filter
        List<Student> students182 = students.stream().filter(stu -> stu.getStature() >= 182).collect(Collectors.toList());
        System.out.println("身高大于等于182的同学有：" + students182);
        // map
        List<String> names = students.stream().map(Student::getName).collect(Collectors.toList());
        System.out.println("学生名单：" + names);

        List<Student> students1 = Arrays.asList(
                rs.registerStudent("丑黄", 22, 184),
                rs.registerStudent("真丑", 23, 185)
        );
        // flatMap
        // 注意与map的区别
        List<Student> all = Stream.of(students, students1).flatMap(Collection::stream).collect(Collectors.toList());
        System.out.println("全部学生：" + all);
        // max
        Optional<Student> maxStatureStudent = all.stream().max(Comparator.comparing(Student::getStature));
        maxStatureStudent.ifPresent(System.out::println);
        // min
        Optional<Student> minAgeStudent = all.stream().min(Comparator.comparing(Student::getAge));
        minAgeStudent.ifPresent(System.out::println);
        // count
        long age20 = all.stream().filter(stu -> stu.getAge() <= 20).count();
        System.out.println("年龄小于等于20的学生数为：" + age20);
        // reduce 初始值，二元操作符
        int allAgeSum = all.stream().map(Student::getAge).reduce(0, Integer::sum);
        System.out.println(allAgeSum);
    }
}
