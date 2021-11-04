package by.epamtc.HacakConstantine.task4.Service.impl;

import by.epamtc.HacakConstantine.task4.Bean.CurrentUser;
import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.DAOFactory;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;
import by.epamtc.HacakConstantine.task4.Service.ClientService;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;


public class ClientServiceImpl implements ClientService {

    @Override
    public void signIn(String login, String password) throws ServiceException{
        if (login == null | login.isEmpty()){
            throw new ServiceException("Incorrect login");
        }
        if (password == null | password.isEmpty()){
            throw new ServiceException("Password is empty");
        }

        try {
            DAOFactory.getInstance().getTxtUsersDAO().authorize(login,password);
        }
        catch (DAOException e){
            throw new ServiceException("Authorization error");
        }


    }
    @Override
    public void signOut(String login) throws ServiceException{
        if (login == null | login.isEmpty()){
            throw new ServiceException("Incorrect login");
        }
        try {
            DAOFactory.getInstance().getTxtUsersDAO().signOut(login);
        }
        catch (DAOException e){
            throw new ServiceException("Signing out error");
        }
    }
    @Override
    public void register(User user) throws ServiceException{
        if (user == null){
            throw new ServiceException("User profile is empty");
        }
        if (user.getLogin() == null | user.getLogin().isEmpty()){
            throw new ServiceException("Login is empty");
        }
        if (!user.getLogin().matches("\\w{5,15}+"))
            throw new ServiceException("Incorrect login");

        if (user.getPassword() == null | user.getPassword().isEmpty()){
            throw new ServiceException("Password is empty");
        }
        if (!user.getPassword().matches(".{8,}+"))
            throw new ServiceException("Wrong password length");
        if (user.getRole() == null){
            throw new ServiceException("Role is empty");
        }

        try {
            DAOFactory.getInstance().getTxtUsersDAO().register(user);
        }
        catch (DAOException e){
             throw new ServiceException("User can't be registered");
        }
    }


    @Override
    public void delete(String login) throws ServiceException{

        if (login == null | login.isEmpty()){
            throw new ServiceException("Login is empty");
        }

        if (CurrentUser.getInstance().getRole()!= Role.ADMIN)
            throw new ServiceException("Insufficient permissions");
        try {
            DAOFactory.getInstance().getTxtUsersDAO().delete(login);
        }
        catch (DAOException e){
            throw new ServiceException("User can't be registered");
        }
    }
}
