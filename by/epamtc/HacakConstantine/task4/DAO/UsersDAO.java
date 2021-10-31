package by.epamtc.HacakConstantine.task4.DAO;

import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

public interface UsersDAO {
    public void registrate(User user) throws DAOException;
    public void authorize(User user) throws DAOException;
}
