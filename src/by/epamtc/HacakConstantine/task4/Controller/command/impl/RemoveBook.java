package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class RemoveBook implements Command {
    final static String success = "Book has been deleted from library";

    @Override
    public String execute(String request) throws ControllerException {
        String response = null;

        try {
            int id=Integer.parseInt(request);
            ServiceFactory.getInstance().getLibraryService().removeBook(id);
            response = success;
        } catch (ServiceException | NumberFormatException e) {
            throw new ControllerException("Can't delete a book, RemoveBook class");
        }

        return response;
    }
}
