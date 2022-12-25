package ru.сourses.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sum1 {
    public static void main(String[] args) throws NumberFormatException{
        List<String> list;
        String str;
        Double sum = 0.0;
        Scanner input = new Scanner(System.in);

        str = input.nextLine();

        list = Arrays.asList(str.split(" "));

        for (String s:list){
            sum += Double.parseDouble(s);
        }

        System.out.println(sum);
    }
}
