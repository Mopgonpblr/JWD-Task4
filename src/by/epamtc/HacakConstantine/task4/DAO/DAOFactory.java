package by.epamtc.HacakConstantine.task4.DAO;

import by.epamtc.HacakConstantine.task4.DAO.impl.TXTBooksDAO;
import by.epamtc.HacakConstantine.task4.DAO.impl.TXTUsersDAO;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final BooksDAO txtBooksDAO = new TXTBooksDAO();
    private final UsersDAO txtUsersDAO = new TXTUsersDAO();

    private DAOFactory(){}

    public static DAOFactory getInstance() {return instance;}

    public BooksDAO getTxtBooksDAO() {
        return txtBooksDAO;
    }

    public UsersDAO getTxtUsersDAO() {
        return txtUsersDAO;
    }
}
