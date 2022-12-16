package ru.сourses.geometry;

public class Square1 {
    private int x;
    private int y;
    private int len;

    public Square1(int x, int y, int len){
        this.x=x;
        this.y=y;
        this.len=Math.abs(len);
//        if(len <= 0) throw new IllegalArgumentException("Длина не может быть <= 0");
//        else this.len=len;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setLen(int len) {
        this.len = Math.abs(len);
        if(len <= 0) throw new IllegalArgumentException("Длина не может быть <= 0");
        else this.len=len;
    }

    @Override
    public String toString() {
        return "Квадрат в точке {" + this.x + "," + this.y + " со стороной " + len;
    }
}
