package by.epamtc.HacakConstantine.task4.Service.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.enums.BookStatus;
import by.epamtc.HacakConstantine.task4.Bean.enums.Role;
import by.epamtc.HacakConstantine.task4.DAO.BooksDAO;
import by.epamtc.HacakConstantine.task4.DAO.DAOFactory;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;
import by.epamtc.HacakConstantine.task4.Service.CurrentUser;
import by.epamtc.HacakConstantine.task4.Service.LibraryService;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;
import by.epamtc.HacakConstantine.task4.Service.validation.BookValidation;
import by.epamtc.HacakConstantine.task4.Service.validation.RoleValidation;

public class LibraryServiceImpl implements LibraryService {

    final static BooksDAO bd = DAOFactory.getInstance().getBooksDAO();

    public void addNewBook(Book book) throws ServiceException {

        if (!BookValidation.isBookValid(book))
            throw new ServiceException("Book data is not correct");
        if (!RoleValidation.isAdmin(CurrentUser.getInstance().getRole()))
            throw new ServiceException("Insufficient permissions");

        try {
            book.setId(bd.loadMedia().size()+1);
            bd.addBook(book);
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book adding error", e);
        }
    }

    @Override
    public void removeBook(int id) throws ServiceException {
        if (id <= 0)
            throw new ServiceException("ID is not valid");
        if (!RoleValidation.isAdmin(CurrentUser.getInstance().getRole()))
            throw new ServiceException("Insufficient permissions");
        try {
            bd.removeBook(id);
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, book removing error", e);
        }
    }

    @Override
    public String findBook(String title) throws ServiceException {
        if (title == null | title.isEmpty()) {
            throw new ServiceException("No title");
        }
        StringBuilder sb = new StringBuilder();
        try {
            for (Book book : bd.loadMedia()) {
                if(book.getTitle().equals(title))
                    sb.append(book.toString() + '\n');
            }
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book finding error", e);
        }
        if (sb == null | sb.length() == 0)
            throw new ServiceException("NOT FOUND");
        return sb.toString();
    }

    @Override
    public String showBooks() throws ServiceException {
        StringBuilder sb = new StringBuilder();
        try {
            for (Book book : bd.loadMedia()) {
                sb.append(book.toString() + '\n');
            }
        } catch (DAOException | NullPointerException e) {
            throw new ServiceException("Service, Book finding error",e);
        }
        if (sb == null | sb.length() == 0)
            throw new ServiceException("NOT FOUND");
        return sb.toString();
    }

    @Override
    public void takeBook(int id) throws ServiceException {
        if (!BookValidation.isIdValid(id)) {
            throw new ServiceException("ID number is not valid");
        }
        if (RoleValidation.isGuest(CurrentUser.getInstance().getRole()))
            throw new ServiceException("Guests should be registered to get a book");
        try {
            for (Book book : bd.loadMedia()) {
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
            throw new ServiceException("Service, Book status error",e);
        }
    }

    @Override
    public void giveBook(int id) throws ServiceException {
        if (!BookValidation.isIdValid(id)) {
            throw new ServiceException("ID number is not valid");
        }
        if (RoleValidation.isGuest(CurrentUser.getInstance().getRole()))
            throw new ServiceException("Sign in to give a book back if you have one");
        try {
            for (Book book : bd.loadMedia()) {
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
            throw new ServiceException("Service, Book status error",e);
        }
    }
}
