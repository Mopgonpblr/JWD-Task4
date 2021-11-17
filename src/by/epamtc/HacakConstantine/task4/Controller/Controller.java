package by.epamtc.HacakConstantine.task4.Controller;

import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;

public class Controller {
    private static final char paramDelimiter = ' ';

    public static String executeTask(String request) throws ControllerException {
        String command;
        String response = null;
        int end = request.indexOf(paramDelimiter);
        if (end == -1) {
            end = request.length();
        }
        command = request.substring(0, end);
        command = command.toUpperCase();

        try {
            response = new CommandProvider().getCommand(command).execute(request.substring(request.indexOf(paramDelimiter) + 1));
        } catch (ControllerException | NullPointerException e) {
            throw new ControllerException("Controller class exception, " + command, e);
        }

        return response;
    }
}
