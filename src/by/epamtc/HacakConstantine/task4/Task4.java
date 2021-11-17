package by.epamtc.HacakConstantine.task4;

import by.epamtc.HacakConstantine.task4.Controller.Controller;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;

import java.util.NoSuchElementException;
import java.util.Scanner;

public class Task4 {
    public static void main(String[] args) {
        String req = "";
        System.out.println("Task 4: Library\n");

        System.out.println("Guest commands\n");

        System.out.println("SIGN_IN LOGIN|PASSWORD");
        System.out.println("REGISTER LOGIN|PASSWORD");
        System.out.println("SHOW_BOOKS");
        System.out.println("FIND_BOOK BOOK TITLE");
        System.out.println("CLOSE\n");

        System.out.println("User commands\n");

        System.out.println("TAKE_BOOK BOOK ID");
        System.out.println("GIVE_BOOK BOOK ID\n");

        System.out.println("Admin commands\n");

        System.out.println("ADD_BOOK AUTHOR|TITLE|NUMBER OF PAGES");
        System.out.println("DELETE_USER LOGIN");
        System.out.println("REMOVE_BOOK BOOK ID\n");

        try (Scanner scan = new Scanner(System.in)) {

            while (true) {

                req = scan.nextLine();
                System.out.println(Controller.executeTask(req));
            }
        } catch (ControllerException | StringIndexOutOfBoundsException e) {
            System.out.println("main() error\n" + e);
        } catch (NoSuchElementException e) {
        }
    }
}

