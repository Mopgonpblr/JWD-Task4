package by.epamtc.HacakConstantine.task4.DAO;

import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.util.ArrayList;

public interface UsersDAO {
    void registerUser(User user) throws DAOException;

    void deleteUser(String login) throws DAOException;

    ArrayList<User> loadUserBase() throws DAOException;
}
