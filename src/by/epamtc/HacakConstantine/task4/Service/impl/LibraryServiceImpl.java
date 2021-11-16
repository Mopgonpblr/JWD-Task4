package by.epamtc.HacakConstantine.task4.Service.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.BookStatus;
import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.DAO.BooksDAO;
import by.epamtc.HacakConstantine.task4.DAO.DAOFactory;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;
import by.epamtc.HacakConstantine.task4.Service.CurrentUser;
import by.epamtc.HacakConstantine.task4.Service.LibraryService;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;

import java.util.ArrayList;

public class LibraryServiceImpl implements LibraryService {

    final static BooksDAO bd = DAOFactory.getInstance().getTxtBooksDAO();

    public void addNewBook(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Book data is empty");
        }
        if (book.getTitle() == null | book.getTitle().isEmpty()) {
            throw new ServiceException("Book has no title");
        }
        if (book.getAuthor() == null | book.getAuthor().isEmpty()) {
            throw new ServiceException("There's no book author");
        }
        if (book.getBookStatus() == null) {
            throw new ServiceException("Book status is unknown");
        }
        if (book.getPageNumber() <= 0) {
            throw new ServiceException("Book has no pages");
        }
        if (CurrentUser.getInstance().getRole() != Role.ADMIN)
            throw new ServiceException("Insufficient permissions");

        try {
            book.setId(bd.getLibrary().size()+1);
            bd.addBook(book);
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book adding error");
        }
    }

    public void editBook(Book book) throws ServiceException {
        if (book == null) {
            throw new ServiceException("Book data is empty");
        }
        if (book.getTitle() == null | book.getTitle().isEmpty()) {
            throw new ServiceException("Book has no title");
        }
        if (book.getAuthor() == null | book.getAuthor().isEmpty()) {
            throw new ServiceException("There's no book author");
        }
        if (book.getBookStatus() == null) {
            throw new ServiceException("Book status is unknown");
        }
        if (book.getPageNumber() == 0) {
            throw new ServiceException("Book has no pages");
        }
        if (CurrentUser.getInstance().getRole() != Role.ADMIN)
            throw new ServiceException("Insufficient permissions");
        try {
            bd.editBook();
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book edit error");
        }
    }

    @Override
    public void removeBook(int id) throws ServiceException {
        if (id <= 0)
            throw new ServiceException("ID is not valid");
        if (CurrentUser.getInstance().getRole() != Role.ADMIN)
            throw new ServiceException("Insufficient permissions");
        try {
            bd.removeBook(id);
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, book removing error");
        }
    }

    @Override
    public String findBook(String title) throws ServiceException {
        if (title == null | title.isEmpty()) {
            throw new ServiceException("No title");
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Book book : bd.find(title)) {
                sb.append(book.toString() + "\n");
            }
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book finding error");
        }
        if (sb == null | sb.length() == 0)
            throw new ServiceException("NOT FOUND");
        return sb.toString();
    }

    @Override
    public String showBooks() throws ServiceException {
        StringBuilder sb = new StringBuilder();
        try {
            for (Book book : bd.getLibrary()) {
                sb.append(book.toString() + "\n");
            }
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book finding error");
        }
        if (sb == null | sb.length() == 0)
            throw new ServiceException("NOT FOUND");
        return sb.toString();
    }

    @Override
    public void takeBook(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("ID number is not valid");
        }
        if (CurrentUser.getInstance().getRole() == Role.GUEST)
            throw new ServiceException("Guests should be registered to get a book");
        try {
            for (Book book : bd.getLibrary()) {
                if (book.getId() == id)
                    if (book.getBookStatus() == BookStatus.TAKEN)
                        throw new ServiceException("The book is already taken");
                    else {
                        book.setOwner(CurrentUser.getInstance().getLogin());
                        book.setBookStatus(BookStatus.TAKEN);
                        bd.updateBook(book);
                        id = 0;
                    }
            }
            if (id != 0)
                throw new ServiceException("There's no book with such ID number in the library");


        } catch (DAOException e) {
            throw new ServiceException("Service, Book status error");
        }
    }

    @Override
    public void giveBook(int id) throws ServiceException {
        if (id <= 0) {
            throw new ServiceException("ID number is not valid");
        }
        if (CurrentUser.getInstance().getRole() == Role.GUEST)
            throw new ServiceException("Sign in to give a book back if you have one");
        try {
            for (Book book : bd.getLibrary()) {
                if (book.getId() == id)
                    if (book.getBookStatus() == BookStatus.AVAILABLE)
                        throw new ServiceException("The book is already in the library");
                    else {
                        book.setOwner("");
                        book.setBookStatus(BookStatus.AVAILABLE);
                        bd.updateBook(book);
                    }
            }
        } catch (DAOException e) {
            throw new ServiceException("Service, Book status error");
        }
    }
}
