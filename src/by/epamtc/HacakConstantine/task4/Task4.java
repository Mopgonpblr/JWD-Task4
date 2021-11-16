package by.epamtc.HacakConstantine.task4;

import by.epamtc.HacakConstantine.task4.Controller.Controller;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;

import java.util.NoSuchElementException;

public class Task4 {
    public static void main(String[] args) {
        String req = "";
        System.out.println("Task 4: Library");
        while (true) {
            try {
                req = NewScanner.sc.nextLine();
                System.out.println(Controller.executeTask(req));
            } catch (ControllerException | StringIndexOutOfBoundsException | NoSuchElementException e) {
                System.out.println("main() error");
            }
        }
    }
}
