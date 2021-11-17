package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class TakeBook implements Command {
    final static String success = "Book has been taken";
    @Override
    public String execute(String request) throws ControllerException {
        String response = null;

        try {
            int id=Integer.parseInt(request);
            ServiceFactory.getInstance().getLibraryService().takeBook(id);
            response = success;
        } catch (ServiceException | NumberFormatException e) {
            throw new ControllerException("Can't take a book, TakeBook class");
        }

        return response;
    }
}
