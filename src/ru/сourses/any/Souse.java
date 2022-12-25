package ru.сourses.any;

import ru.сourses.geometry.Line1;
import ru.сourses.geometry.Point1;

import java.awt.*;

class Spicy{
    private String txt;

    public static final Spicy VERY_SPICY = new Spicy("Очень острый");
    public static final Spicy SPICY = new Spicy("Острый");
    public static final Spicy LOW_SPICY = new Spicy("Не острый");

    private Spicy(String txt){
        this.txt = txt;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    @Override
    public String toString() {
        return txt;
    }
}

public class Souse {
    String name;
    Spicy spicy;

    public Souse(String name, Spicy spicy){
        this.name = name;
        this.spicy = spicy;
    }

    public String info(){
        return this.name + ":" + this.spicy;
    }
}

class Main{
    public static void main(String[] args) throws CloneNotSupportedException {
        Souse s2 = new Souse("Шрирача", Spicy.SPICY);

        System.out.println(s2.info());
    }
}
