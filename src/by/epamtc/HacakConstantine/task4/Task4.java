package by.epamtc.HacakConstantine.task4;

import by.epamtc.HacakConstantine.task4.Controller.Controller;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;

public class Task4 {
    public static void main(String[] args) {
        String req = "";
        while (!req.equals("EXIT")) {
            req = NewScanner.sc.nextLine();

            try {
                System.out.println(Controller.executeTask(req));
            } catch (ControllerException e) {
                System.out.println("main() error");
            }
        }

    }
}
