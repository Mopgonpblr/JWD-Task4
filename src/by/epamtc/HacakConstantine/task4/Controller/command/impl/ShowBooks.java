package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class ShowBooks implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        String response = null;

        try {
            response = ServiceFactory.getInstance().getLibraryService().showBooks();
        } catch (ServiceException | NullPointerException e) {
            throw new ControllerException("Can't find the book, FindBook class");
        }

        return response;
    }
}