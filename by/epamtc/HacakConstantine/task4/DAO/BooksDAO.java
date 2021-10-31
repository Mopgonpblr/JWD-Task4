package by.epamtc.HacakConstantine.task4.DAO;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.util.*;

public interface BooksDAO {
    public void addBook(Book book) throws DAOException;
    public void removeBook (Book book) throws DAOException;
    public List<Book> find(String title) throws DAOException;
}
