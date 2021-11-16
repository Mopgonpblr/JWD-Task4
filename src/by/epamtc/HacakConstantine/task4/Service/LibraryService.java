package by.epamtc.HacakConstantine.task4.Service;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

import java.util.ArrayList;


public interface LibraryService {
    void addNewBook (Book book) throws ServiceException;
    void editBook (Book book) throws ServiceException;
    void takeBook(int id) throws ServiceException;
    void giveBook(int id) throws ServiceException;
    void removeBook (int id) throws ServiceException;
    String findBook(String title) throws ServiceException;
    String showBooks() throws ServiceException;
}
