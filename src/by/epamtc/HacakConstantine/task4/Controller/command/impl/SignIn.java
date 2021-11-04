package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Bean.CurrentUser;
import by.epamtc.HacakConstantine.task4.Bean.UserStatus;
import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Service.ClientService;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;


public class SignIn implements Command {
    @Override
    public String execute(String request){

        String response = null;

        String[] req = request.split("\\|");
        String login = req[0];
        String password = req[1];

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ClientService clientService = serviceFactory.getClientService();

        try{
            if (CurrentUser.getInstance().getStatus()== UserStatus.ONLINE)
                clientService.signOut(CurrentUser.getInstance().getLogin());
            clientService.signIn(login,password);
            response = "Welcome "+login;
        }
        catch (ServiceException e){
            response = "Error. Can't log in";
        }
        return  response;
    }
}
