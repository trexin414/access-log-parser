package ru.сourses.geometry;

import java.util.ArrayList;
import java.util.Arrays;
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
    public boolean equals(Object obj) {
        Boolean check;
        if (this == obj) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        Polyline1 p = (Polyline1) obj;
        if (this.arr.size() != p.arr.size()) return false;
        for (Point first: this.arr){
            check = false;
            for (Point second:p.arr){
                if (first.equals(second)) check = true;
            }
            if (!check) return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "Polyline{" +
                "arr=" + arr +
                '}';
    }

    public static void main(String[] args) throws CloneNotSupportedException {
        Polyline1 l1 = new Polyline1(Arrays.asList(new Point(1,1), new Point(2,2), new Point(3,3)));
        Polyline1 l2 = new Polyline1(Arrays.asList(new Point(2,2), new Point(1,1), new Point(3,3)));
        Polyline1 l3 = new Polyline1(Arrays.asList(new Point(2,2), new Point(1,1), new Point(4,4)));

        System.out.println(l1.equals(l2));
        System.out.println(l2.equals(l3));
    }
}
