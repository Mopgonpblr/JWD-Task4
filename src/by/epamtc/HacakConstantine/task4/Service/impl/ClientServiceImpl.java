package by.epamtc.HacakConstantine.task4.Service.impl;

import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.DAOFactory;
import by.epamtc.HacakConstantine.task4.DAO.UsersDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;
import by.epamtc.HacakConstantine.task4.Service.ClientService;
import by.epamtc.HacakConstantine.task4.Service.CurrentUser;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;


public class ClientServiceImpl implements ClientService {

    final static UsersDAO ud = DAOFactory.getInstance().getTxtUsersDAO();

    @Override
    public void signIn(String login, String password) throws ServiceException{
        if (login == null | login.isEmpty()){
            throw new ServiceException("Incorrect login");
        }
        if (password == null | password.isEmpty()){
            throw new ServiceException("Password is empty");
        }

        try {
            for (User u:ud.getUserBase())
                if (u.getLogin().equals(login))
                    if (u.getPassword().equals(password))
                        CurrentUser.getInstance().setUser(ud.authorize(login, password));
                    else
                        throw new ServiceException("Wrong password");
        }
        catch (DAOException e){
            throw new ServiceException("Authorization error");
        }


    }
    @Override
    public void signOut() throws ServiceException{
        if (CurrentUser.getInstance() == null){
            throw new ServiceException("Incorrect login");
        }
        try {
            ud.signOut(CurrentUser.getInstance().getLogin());
            CurrentUser.getInstance().setUser(new User());
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
            for (User u: ud.getUserBase())
                if (u.getLogin().equals(user.getLogin()))
                    throw new ServiceException("This login is taken. Please, choose another one");
            user.setId(ud.getUserBase().size()+1);
            ud.register(user);
            CurrentUser.getInstance().setUser(user);
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
            for (User u: ud.getUserBase())
                if (u.getLogin().equals(login))
                    ud.delete(login);
        }
        catch (DAOException e){
            throw new ServiceException("User can't be deleted");
        }
    }
}
