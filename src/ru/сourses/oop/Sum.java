package ru.сourses.oop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Sum {
    public static void main(String[] args) {
        List<String> list;
        String str;
        Double sum = 0.0;
        Scanner input = new Scanner(System.in);

        str = input.nextLine();

        list = Arrays.asList(str.split(" "));

        for (String s:list){
            try {
                sum += Double.parseDouble(s);
            } catch (NumberFormatException ex){
                sum += 0;
            }
        }

        System.out.println(sum);
    }
}
