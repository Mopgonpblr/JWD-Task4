package by.epamtc.HacakConstantine.task4.DAO;

import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

public interface UsersDAO {
    void register(User user) throws DAOException;

    void authorize(String login, String password) throws DAOException;

    void signOut(String login) throws DAOException;

    void delete(String login) throws DAOException;
}
