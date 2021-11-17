package by.epamtc.HacakConstantine.task4.Service.validation;

import by.epamtc.HacakConstantine.task4.Bean.Book;

public class BookValidation {
    public static boolean isBookValid(Book book){
        if (book == null) {
            return false;
        }
        if (book.getTitle() == null | book.getTitle().isEmpty()) {
            return false;
        }
        if (book.getAuthor() == null | book.getAuthor().isEmpty()) {
            return false;
        }
        if (book.getBookStatus() == null) {
            return false;
        }
        if (book.getPageNumber() <= 0) {
            return false;
        }
        return true;
    }

    public static boolean isIdValid(int id){
        return  id >= 0;
    }
}
