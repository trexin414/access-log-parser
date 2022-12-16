package ru.сourses.geometry;

import java.util.ArrayList;
import java.util.List;

public class Polyline1 {
    List<Point> arr;

    public Polyline1(){

    }

    public Polyline1(List<Point> list){
        this.arr = list;
    }

    public List<Line> returnLineArr(){
        List<Line> arrLine = new ArrayList<>();
        for(int i = 1; i < this.arr.size(); i++){
            arrLine.add(new Line(arr.get(i-1), arr.get(i)));
        }
        return arrLine;
    }

    public void setPoint(int pos, int x, int y){
        this.arr.get(pos).setX(x);
        this.arr.get(pos).setY(y);
    }

    public double returnLineSize(){
        double size = 0;

        for(int i = 1; i < this.arr.size(); i++){
            size += new Line(arr.get(i-1), arr.get(i)).lineSize();
        }
        return size;
    }

    @Override
    public String toString() {
        return "Polyline{" +
                "arr=" + arr +
                '}';
    }
}
