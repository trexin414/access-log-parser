package ru.сourses.any;
import java.util.ArrayList;
import java.util.List;

public class Student1 {
    private String name;
    private List<Integer> rating = new ArrayList<>();

    public Student1(String name, List<Integer> rating) {
        this.name = name;
        for (Integer i:rating) addI(i);
    }

    public void addRating(Integer n) {
        addI(n);
    }

    public List<Integer> getRating() {
        return rating;
    }

    private void addI(Integer i){
        if (i > 1 && i < 6){
            this.rating.add(i);
        } else throw new IllegalArgumentException("Оценка должна быть от 2 до 5");
    }

    @Override
    public String toString() {
        return this.name + this.rating.toString();
    }
}
