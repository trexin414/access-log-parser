package ru.сourses.any;

import java.util.Objects;

public class Fraction1 implements Cloneable{

    int num, denum;

    public Fraction1(int num, int denum) {
        this.num = num;
        this.denum = denum;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Fraction1 f = (Fraction1) obj;
        return Objects.equals(this.num, f.num) && Objects.equals(this.denum, f.denum);
    }

//    public boolean equals(Fraction1 f){
//        return this.num == f.num && this.denum == f.denum;
//    }

    @Override
    protected Fraction1 clone() throws CloneNotSupportedException {
        return (Fraction1) super.clone();
//        return new Fraction1(this.num, this.denum);
    }

    public String toString() {
        return num + "/" + denum;
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Fraction1 f1 = new Fraction1(2,3);

        Fraction1 f2 = f1.clone();

        System.out.println(f1.equals(f2));
    }
}
