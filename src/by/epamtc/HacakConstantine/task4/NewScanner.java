package by.epamtc.HacakConstantine.task4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewScanner {
    static Scanner sc = new Scanner(System.in);

    public static int scanInt(){
        while (!sc.hasNextInt()) {
            sc.next();
        }
        return sc.nextInt();
    }

    public static int scanYear() {
        int year;
        do {
            while (!sc.hasNextInt()) {
                sc.next();
            }
            year = sc.nextInt();
        }
        while (year > 2021);
        return year;
    }

    public static int scanMonth() {
        int month;
        do {
            while (!sc.hasNextInt()) {
                sc.next();
            }
            month = sc.nextInt();
        } while (month < 1 || month > 12);
        return month;
    }

    public static double scanDouble() {
        while (!sc.hasNextDouble()) {
            sc.next();
        }
        return sc.nextDouble();
    }

    public static int scanSeconds() {
        int seconds;
        do {
            while (!sc.hasNextInt()) {
                sc.next();
            }
            seconds = sc.nextInt();
        } while (seconds > 86400 || seconds < 1);
        return seconds;
    }

    public static String scanFile(String path) throws FileNotFoundException {
        StringBuilder info= new StringBuilder();
        File file = new File(path);
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine())
            info.append("\n").append(sc.nextLine());
        return info.toString();
    }

}
