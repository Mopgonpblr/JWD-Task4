package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class DeleteUser implements Command {
    final static String success = "User has been deleted from library";
    @Override
    public String execute(String request) throws ControllerException {
        String response = null;
        try {
            ServiceFactory.getInstance().getClientService().delete(request);
            response = success;
        } catch (ServiceException | NumberFormatException e) {
            throw new ControllerException("Can't delete a user, DeleteUser class");
        }

        return response;
    }
}
