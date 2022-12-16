package ru.сourses.geometry;

class Figures{
    int x;
    int y;
    int line = 0;

    static final double PI = Math.PI;

    public Figures(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Figures(int x, int y, int line){
        this.x = x;
        this.y = y;
        this.line = line;
    }
}

class Circle extends Figures implements Formulable{
    public Circle(int x, int y, int radius){
        super(x, y, radius);
    }

    public double area(){
        return PI * super.line * super.line;
    }
}

class Square extends Figures implements Formulable{
    public Square(int x, int y, int line){
        super(x,y,line);
    }

    public double area(){
        return super.line * super.line;
    }
}

class Rectangle extends Figures implements Formulable{
    int line1;
    int line2;


    public Rectangle(int x, int y, int line1, int line2){
        super(x, y);
        this.line1 = line1;
        this.line2 = line2;
    }
    public double area(){
        return this.line1 * this.line2;
    }


    public static void main(String[] args) {
        Circle c1 = new Circle(0, 0, 3);
        Circle c2 = new Circle(0, 0, 4);
        Square s1 = new Square(5, 5, 3);
        Square s2 = new Square(5, 5, 4);
        Rectangle r1 = new Rectangle(5, 5, 2, 3);
        Rectangle r2 = new Rectangle(5, 5, 3, 4);
        figures(c1, c2, s1, s2, r1, r2);
    }

    public static void figures(Formulable... formulables){
        double a = 0;
        for(Formulable f:formulables){
            a += f.area();
        }
        System.out.println(a);
    }

}
public interface Formulable{
    double area();
}


