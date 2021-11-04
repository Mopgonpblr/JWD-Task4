package by.epamtc.HacakConstantine.task4.DAO;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.util.*;

public interface BooksDAO {
     void addBook(Book book) throws DAOException;
     void removeBook(int id) throws DAOException;
     void editBook() throws DAOException;
     ArrayList<Book> find(String title) throws DAOException;
}
