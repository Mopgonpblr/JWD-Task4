package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class Close implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        try {
            System.exit(0);
        } catch (NumberFormatException e) {
            throw new ControllerException("Can't delete a user, DeleteUser class");
        }
        return null;
    }
}
