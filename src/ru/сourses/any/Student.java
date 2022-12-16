package ru.сourses.any;
import java.util.ArrayList;
import java.util.List;

public final class Student{
    private List grades=new ArrayList<>();
    private Rule rule;
    String name;

    public Student(String name, Rule rule) {
        this.name = name;
        this.rule = rule;
    }
    public void addGrade(int grade){
        if(rule.check(grade)) grades.add(grade);
    }

    public String toString() {
        return "Student{" + " grades =" + grades + ", name=" + name + '}';
    }
}
interface Rule{
    boolean check(int grade);
}
