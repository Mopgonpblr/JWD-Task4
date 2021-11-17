package by.epamtc.HacakConstantine.task4.Service.impl;

import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.*;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;
import by.epamtc.HacakConstantine.task4.Service.ClientService;
import by.epamtc.HacakConstantine.task4.Service.CurrentUser;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;
import by.epamtc.HacakConstantine.task4.Service.validation.RoleValidation;
import by.epamtc.HacakConstantine.task4.Service.validation.UserValidation;

public class ClientServiceImpl implements ClientService {

    final static UsersDAO ud = DAOFactory.getInstance().getUsersDAO();

    @Override
    public void signIn(String login, String password) throws ServiceException {
        if (!UserValidation.isLoginValid(login)) {
            throw new ServiceException("Incorrect login");
        }
        if (!UserValidation.isPasswordValid(password)) {
            throw new ServiceException("Incorrect password");
        }

        try {
            for (User u : ud.loadUserBase())
                if (u.getLogin().equals(login))
                    if (u.getPassword().equals(password))
                        CurrentUser.getInstance().setUser(u);
                    else
                        throw new ServiceException("Wrong password");
        } catch (DAOException e) {
            throw new ServiceException("Authorization error", e);
        }


    }

    @Override
    public void signOut() throws ServiceException {
        if (CurrentUser.getInstance() == null) {
            throw new ServiceException("You're not signed in");
        }
        CurrentUser.getInstance().setUser(new User());

    }

    @Override
    public void register(User user) throws ServiceException {
        if (!UserValidation.isUserValid(user))
            throw new ServiceException("Incorrect user data");
        try {
            for (User u : ud.loadUserBase())
                if (u.getLogin().equals(user.getLogin()))
                    throw new ServiceException("This login is taken. Please, choose another one");
            user.setId(ud.loadUserBase().size() + 1);
            ud.registerUser(user);
            CurrentUser.getInstance().setUser(user);
        } catch (DAOException e) {
            throw new ServiceException("User can't be registered", e);
        }
    }


    @Override
    public void delete(String login) throws ServiceException {

        if (!UserValidation.isLoginValid(login)) {
            throw new ServiceException("Incorrect login");
        }

        if (!RoleValidation.isAdmin(CurrentUser.getInstance().getRole()))
            throw new ServiceException("Insufficient permissions");
        try {
            for (User u : ud.loadUserBase())
                if (u.getLogin().equals(login))
                    ud.deleteUser(login);
        } catch (DAOException e) {
            throw new ServiceException("User can't be deleted",e);
        }
    }
}
