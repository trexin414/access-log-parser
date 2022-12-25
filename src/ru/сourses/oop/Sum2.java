package ru.сourses.oop;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sum2 {
    public static void main(String[] args){
        List<String> list;
        String str;
        Double sum = 0.0;
        Scanner input = new Scanner(System.in);
        Pattern pattern = Pattern.compile("^\\d+$|^-\\d+$|^\\d\\.\\d+$|^-\\d+\\.\\d+$");
        Matcher matcher;

        str = input.nextLine();

        list = Arrays.asList(str.split(" "));

        for (String s:list){
            matcher = pattern.matcher(s);
            if(matcher.find())
                sum += Double.parseDouble(s);
        }

        System.out.println(sum);
    }
}
