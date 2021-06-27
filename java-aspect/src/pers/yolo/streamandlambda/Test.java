package pers.yolo.streamandlambda;

import pers.yolo.streamandlambda.pojo.Student;

import java.util.function.*;

public class Test {

    public static void main(String[] args) {
        Student stuZS = new Student("张三", 18, 177);
        // 断言，判断
        Predicate<Integer> predicate = x -> x > 160;
        System.out.println("身高是否大于160：" + predicate.test(stuZS.getStature()));
        // 消费
        Consumer<String> consumer = System.out::println;
        consumer.accept(stuZS.toString());
        // 函数
        Function<Student, String> function = Student::getName;
        System.out.println(function.apply(stuZS));
        // 供应商，生产者，工厂
        Supplier<Student> supplier = Student::new;
        Student stu1 = supplier.get();
        Student stu2 = supplier.get();
        System.out.println(stu1 == stu2);
        // 一元操作符
        UnaryOperator<Integer> unaryOperator = x -> ++x;
        System.out.println(unaryOperator.apply(stuZS.getAge()));
        // 二元操作符
        BinaryOperator<String> binaryOperator = (str1, str2) -> str1 + str2;
        System.out.println(
                binaryOperator.apply(stuZS.getName(), String.valueOf(stuZS.getAge()))
        );
        // 自定义操作
        RegisterStudent<Student> mfi = Student::new;
        Student stuLS = mfi.registerStudent("李四", 20, 180);
        System.out.println(stuLS);
    }

}

// FunctionInterface
interface RegisterStudent<Student> {
    Student registerStudent(String name, int age, int stature);
}
