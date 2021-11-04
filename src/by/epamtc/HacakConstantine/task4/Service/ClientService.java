package by.epamtc.HacakConstantine.task4.Service;

import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;


public interface ClientService {
    void signIn(String login, String password) throws ServiceException;

    void signOut(String login) throws ServiceException;

    void register(User user) throws ServiceException;

    void delete(String login)    throws ServiceException;
}
