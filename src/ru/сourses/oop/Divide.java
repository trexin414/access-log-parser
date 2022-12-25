package ru.сourses.oop;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Divide {
    public static void main(String[] args) throws FileNotFoundException, OperationAttemptException {
        System.out.println(divide("./test"));
    }

    public static int divide(String fileName) throws FileNotFoundException, OperationAttemptException {
        File f=new File(fileName);
        Scanner sc= new Scanner(f);

        try {
            return sc.nextInt()/sc.nextInt();
        } catch (NoSuchElementException | ArithmeticException ex) {
            throw new OperationAttemptException("0");
        } finally {
            sc.close();
        }

    }
}
