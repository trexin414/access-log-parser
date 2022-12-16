package ru.сourses.any;
public class Fraction extends Number{
    private int numerator;
    private int divider;

    public Fraction(int i){
        this.numerator = i;
        this.divider = 1;
    }

    public Fraction(double i){
        this.numerator = (int)(i * 10);
        this.divider = 10;
    }

    public Fraction(int numerator, int divider){
        this.numerator=numerator;
        if(divider > 0) this.divider=divider;
        else throw new IllegalArgumentException("Знаменатель не может быть отрицательным или 0");
    }

    public Fraction sum(int n){
        return new Fraction(this.numerator + n * this.divider, this.divider);
    }

    public Fraction sum(Fraction f){
        return new Fraction(this.numerator * f.getDivider() + this.divider * f.getNumerator(), this.divider * f.getDivider());
    }

    public Fraction minus(int n){
        return new Fraction(this.numerator - n * this.divider, this.divider);
    }

    public Fraction minus(Fraction f){
        return new Fraction(this.numerator * f.getDivider() - this.divider * f.getNumerator(), this.divider * f.getDivider());
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDivider() {
        return divider;
    }

    @Override
    public String toString() {
        return numerator + "/" + divider;
    }

    @Override
    public int intValue() {
        return 0;
    }

    @Override
    public long longValue() {
        return 0;
    }

    @Override
    public float floatValue() {
        return 0;
    }

    @Override
    public double doubleValue() {
        return 0;
    }
}
