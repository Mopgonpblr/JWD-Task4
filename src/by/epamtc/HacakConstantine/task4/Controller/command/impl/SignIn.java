package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Bean.enums.UserStatus;
import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ClientService;
import by.epamtc.HacakConstantine.task4.Service.CurrentUser;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;


public class SignIn implements Command {
    final static String success = "Welcome ";
    @Override
    public String execute(String request) throws ControllerException{

        String response = null;

        String[] req = request.split("\\|");
        String login = req[0];
        String password = req[1];

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try {
            if (CurrentUser.getInstance().getStatus() == UserStatus.ONLINE)
                clientService.signOut();
            clientService.signIn(login, password);
            if (CurrentUser.getInstance().getName().isEmpty())
                response = success + login;
            else
                response = success + CurrentUser.getInstance().getName();
        } catch (ServiceException e) {
           throw new ControllerException( "Error. Can't log in");
        }
        return response;
    }
}
