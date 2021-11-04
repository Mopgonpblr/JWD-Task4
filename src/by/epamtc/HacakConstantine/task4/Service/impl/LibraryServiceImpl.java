package by.epamtc.HacakConstantine.task4.Service.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.CurrentUser;
import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.DAO.DAOFactory;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;
import by.epamtc.HacakConstantine.task4.Service.LibraryService;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

import java.util.ArrayList;

public class LibraryServiceImpl implements LibraryService {
    public void addNewBook (Book book) throws ServiceException{
        if (book == null){
            throw new ServiceException("Book data is empty");
        }
        if (book.getTitle() == null | book.getTitle().isEmpty()){
            throw new ServiceException("Book has no title");
        }
        if (book.getAuthor() == null | book.getAuthor().isEmpty()){
            throw new ServiceException("There's no book author");
        }
        if (book.getBookStatus() == null){
            throw new ServiceException("Book status is unknown");
        }
        if (book.getPageNumber() == 0){
            throw new ServiceException("Book has no pages");
        }
        if (CurrentUser.getInstance().getRole()!= Role.ADMIN)
            throw new ServiceException("Insufficient permissions");

         try{
             DAOFactory.getInstance().getTxtBooksDAO().addBook(book);
         }
         catch (DAOException | NullPointerException e){
             throw new ServiceException("Service, Book adding error");
         }
    }
    public void editBook (Book book) throws ServiceException{
        if (book == null){
            throw new ServiceException("Book data is empty");
        }
        if (book.getTitle() == null | book.getTitle().isEmpty()){
            throw new ServiceException("Book has no title");
        }
        if (book.getAuthor() == null | book.getAuthor().isEmpty()){
            throw new ServiceException("There's no book author");
        }
        if (book.getBookStatus() == null){
            throw new ServiceException("Book status is unknown");
        }
        if (book.getPageNumber() == 0){
            throw new ServiceException("Book has no pages");
        }
        if (CurrentUser.getInstance().getRole()!= Role.ADMIN)
            throw new ServiceException("Insufficient permissions");
        try{
            DAOFactory.getInstance().getTxtBooksDAO().editBook();
        }
        catch (DAOException | NullPointerException e){
            throw new ServiceException("Service, Book edit error");
        }
    }

    @Override
    public void removeBook(int id) throws ServiceException {
        if (id<=0)
          throw new ServiceException("ID is not valid");
        if (CurrentUser.getInstance().getRole()!= Role.ADMIN)
            throw new ServiceException("Insufficient permissions");
        try{
            DAOFactory.getInstance().getTxtBooksDAO().removeBook(id);
        }
        catch (DAOException | NullPointerException e){
            throw new ServiceException("Service, book removing error");
        }
    }

    @Override
    public String findBook(String title) throws ServiceException {
        if (title == null | title.isEmpty()){
            throw new ServiceException("No title");
        }
        StringBuilder sb=new StringBuilder();
        try{
           ArrayList<Book> bookResults = DAOFactory.getInstance().getTxtBooksDAO().find(title);
           for (Book book : bookResults){
               sb.append(book.toString()+"\n");
           }
        }
        catch (DAOException | NullPointerException e){
            throw new ServiceException("Service, Book finding error");
        }
        if (sb==null | sb.length()==0)
            return "NOT FOUND";
        return sb.toString();
    }
}
