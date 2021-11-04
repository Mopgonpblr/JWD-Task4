package by.epamtc.HacakConstantine.task4.Controller;

import by.epamtc.HacakConstantine.task4.Controller.command.impl.*;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;

public class Controller {
    private static final char paramDelimiter = ' ';

    public static String executeTask(String request) throws ControllerException {
        String command;
        command = request.substring(0, request.indexOf(paramDelimiter));
        command = command.toUpperCase();

        String response = null;

        switch (command) {
            case "SIGN_IN":
                try {
                    response = new SignIn().execute(request.substring(request.indexOf(paramDelimiter) + 1));
                } catch (NullPointerException e) {
                    throw new ControllerException("Controller class exception, SIGN_IN");
                }
                break;
            case "REGISTER":
                try {
                    response = new Register().execute(request.substring(request.indexOf(paramDelimiter) + 1));
                } catch (ControllerException e) {
                    throw new ControllerException("Controller class exception, REGISTER");
                }
                break;
            case "DELETE_USER":
                try {
                    response = new DeleteUser().execute(request.substring(request.indexOf(paramDelimiter) + 1));
                } catch (NullPointerException e) {
                    throw new ControllerException("Controller class exception, DELETE_USER");
                }
                break;
            case "ADD_BOOK":
                try {
                    response = new AddBook().execute(request.substring(request.indexOf(paramDelimiter) + 1));
                } catch (ControllerException e) {
                    throw new ControllerException("Controller class exception, ADD_BOOK");
                }
                break;
            case "REMOVE_BOOK":
                try {
                    response = new RemoveBook().execute(request.substring(request.indexOf(paramDelimiter) + 1));
                } catch (ControllerException e) {
                    throw new ControllerException("Controller class exception, REMOVE_BOOK");
                }
                break;
            case "FIND_BOOK":
                try {
                    response = new FindBook().execute(request.substring(request.indexOf(paramDelimiter) + 1));
                } catch (ControllerException e) {
                    throw new ControllerException("Controller class exception, FIND_BOOK");
                }
                break;
            default:
                response = new WrongCommand().execute(request);
        }
        return response;
    }
}
