package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.enums.BookStatus;
import by.epamtc.HacakConstantine.task4.DAO.BooksDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.*;
import java.util.*;

public class TXTBooksDAO implements BooksDAO {

    final static String path = "Resources/Library.txt";
    private static final String paramDelimiter = "|";
    private static final String lineEnd = ".+\n";

    @Override
    public void addBook(Book book) throws DAOException {
        try {
            FileInputOutput.write(book.toString(),path,true);
        } catch (DAOException e) {
            throw new DAOException("File not found", e);
        }
    }

    @Override
    public void removeBook(int id) throws DAOException {

        try {
            String inputStr = FileInputOutput.read(path);
            inputStr = inputStr.replaceFirst(id + lineEnd, "");
            FileInputOutput.write(inputStr,path,false);
        } catch (DAOException e) {
            throw new DAOException("File not found", e);
        }
    }


    public ArrayList<Book> loadMedia() throws DAOException {
        ArrayList<Book> library = new ArrayList<>();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s;
            while ((s = in.readLine()) != null) {
                String[] req = s.split("\\" + paramDelimiter);
                Book newBook = new Book();
                newBook.setId(Integer.valueOf(req[0]));
                newBook.setTitle(req[1]);
                newBook.setAuthor(req[2]);
                newBook.setPageNumber(Integer.valueOf(req[3]));
                newBook.setBookStatus(BookStatus.valueOf(req[4]));
                if (req.length > 5)
                    newBook.setOwner(req[5]);
                library.add(newBook);
            }
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
        return library;
    }

    @Override
    public void updateBook(Book book) throws DAOException {
        try {
            String inputStr = FileInputOutput.read(path);
            StringBuilder originalStr = new StringBuilder();
            originalStr.append(book.getId()).append(paramDelimiter).append(book.getTitle()).append(lineEnd);
            inputStr = inputStr.replaceFirst(originalStr.toString(), book.toString());
            FileInputOutput.write(inputStr,path,false);
        } catch (DAOException e) {
            throw new DAOException("File not found", e);
        }
    }
}
