package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.BookStatus;
import by.epamtc.HacakConstantine.task4.Controller.command.Command;
import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;
import by.epamtc.HacakConstantine.task4.Service.ServiceFactory;
import by.epamtc.HacakConstantine.task4.Service.exception.ServiceException;


public class AddBook implements Command {
    @Override
    public String execute(String request) throws ControllerException {
        String response = null;

        String[] req = request.split("\\|");
        Book newBook = new Book();
        newBook.setId(1);
        newBook.setBookStatus(BookStatus.AVAILABLE);
        newBook.setAuthor(req[0]);
        newBook.setTitle(req[1]);
        newBook.setPageNumber(Integer.valueOf(req[2]));
        newBook.setOwner("");
        try {
            ServiceFactory.getInstance().getLibraryService().addNewBook(newBook);
            response = "Book has been added to library";
        } catch (ServiceException | ArrayIndexOutOfBoundsException e) {
            throw new ControllerException("Can't add a book");
        }

        return response;
    }
}
