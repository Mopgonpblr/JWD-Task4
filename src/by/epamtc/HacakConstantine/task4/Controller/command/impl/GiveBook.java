package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class GiveBook implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        String response = null;

        try {
            int id=Integer.parseInt(request);
            ServiceFactory.getInstance().getLibraryService().giveBook(id);
            response = "Book has been given back to library";
        } catch (ServiceException | NumberFormatException e) {
            throw new ControllerException("Can't take a book, GiveBook class");
        }

        return response;
    }
}
