package pers.yolo.streamandlambda.pojo;

import java.util.List;

public class OutstandingClass {
    private String className;

    private List<Student> students;

    public OutstandingClass() {
    }

    public OutstandingClass(String className, List<Student> students) {
        this.className = className;
        this.students = students;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "OutstandingClass{" +
                "className='" + className + '\'' +
                ", students=" + students +
                '}';
    }
}
