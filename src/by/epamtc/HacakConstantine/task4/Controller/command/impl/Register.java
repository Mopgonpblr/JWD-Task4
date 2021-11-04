package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Bean.CurrentUser;
import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.Bean.UserStatus;
import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

public class Register implements Command {
    public String execute(String request) throws ControllerException {
        String response = null;

        String[] req = request.split("\\|");
        User newUser = new User();
        newUser.setId(1);
        newUser.setLogin(req[0]);
        newUser.setPassword(req[1]);
        newUser.setRole(Role.valueOf(req[2].toUpperCase()));
        newUser.setName(" ");
        try {
            if (CurrentUser.getInstance().getStatus() == UserStatus.ONLINE)
                ServiceFactory.getInstance().getClientService().signOut(CurrentUser.getInstance().getLogin());
            ServiceFactory.getInstance().getClientService().register(newUser);
            response = "User has been registered successfully";
        } catch (ServiceException e) {
            throw new ControllerException("Can't register new user");
        }
        System.out.println(newUser.toString());

        return response;
    }
}
