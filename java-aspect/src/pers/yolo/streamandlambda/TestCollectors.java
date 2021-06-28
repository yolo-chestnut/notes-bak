package pers.yolo.streamandlambda;

import pers.yolo.streamandlambda.pojo.OutstandingClass;
import pers.yolo.streamandlambda.pojo.SpecialityEnum;
import pers.yolo.streamandlambda.pojo.Student;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TestCollectors {
    public static void main(String[] args) {
        List<Student> students1 = Arrays.asList(
                new Student("黄1", 16, 170, Arrays.asList(SpecialityEnum.DANCE, SpecialityEnum.RUNNING)),
                new Student("黄2", 17, 171, Arrays.asList(SpecialityEnum.SING, SpecialityEnum.SWIMMING)),
                new Student("黄3", 18, 172, Arrays.asList(SpecialityEnum.SWIMMING, SpecialityEnum.RUNNING)),
                new Student("黄4", 19, 173, Arrays.asList(SpecialityEnum.SWIMMING, SpecialityEnum.DANCE))
        );
        OutstandingClass class1 = new OutstandingClass("1班", students1);
        List<Student> students2 = new ArrayList<>(students1);
        students2.remove(0);
        OutstandingClass class2 = new OutstandingClass("2班", students2);
        // Collectors.maxBy()
//        Optional<OutstandingClass> moreStudentsClass = Stream.of(class1, class2).collect(Collectors.maxBy(Comparator.comparing(c -> c.getStudents().size())));
        Optional<OutstandingClass> moreStudentsClass = Stream.of(class1, class2).max(Comparator.comparing(c -> c.getStudents().size()));
        moreStudentsClass.ifPresent(System.out::println);
        // Collectors.averagingInt()
        double averagingAge = students1.stream().collect(Collectors.averagingInt(Student::getAge));
        System.out.println("1班学生平均年龄：" + averagingAge);
        // Collectors.partitioningBy()
        Map<Boolean, List<Student>> canDance = students1.stream().collect(Collectors.partitioningBy(stu -> stu.getSpecialities().contains(SpecialityEnum.DANCE)));
        System.out.println("会跳舞的学生有：" + canDance.get(Boolean.TRUE));
        System.out.println("不会跳舞的学生：" + canDance.get(Boolean.FALSE));
        // Collectors.groupingBy()
        Map<SpecialityEnum, List<Student>> groups = students1.stream().collect(Collectors.groupingBy(stu -> stu.getSpecialities().get(0)));
        groups.forEach((k, v) -> System.out.println(k + "：" + v));
        // Collectors.joining()
        String nameStr = students1.stream().map(Student::getName).collect(Collectors.joining(",", "<", ">"));
        System.out.println(nameStr);
    }
}
