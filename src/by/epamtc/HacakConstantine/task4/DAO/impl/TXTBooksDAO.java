package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.BookStatus;
import by.epamtc.HacakConstantine.task4.BufferedInputFile;
import by.epamtc.HacakConstantine.task4.DAO.BooksDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.*;
import java.util.*;

public class TXTBooksDAO implements BooksDAO {

    final static String path = "Resources/Library.txt";

    @Override
    public void addBook(Book book) throws DAOException {

        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(path,true)))) {

            out.write(book.toString());
            out.close();
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }

    @Override
    public void removeBook(int id) throws DAOException {
        try {
            String inputStr = BufferedInputFile.read(path);

            inputStr = inputStr.replaceFirst(id + ".+\n", "");

            FileOutputStream fileOut = new FileOutputStream(path);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            //System.out.println(BufferedInputFile.read(path()));
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }


    public ArrayList<Book> getLibrary() throws DAOException {
        ArrayList<Book> library = new ArrayList<>();
        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(path));
            String s;
            while ((s = in.readLine()) != null) {
                String[] req = s.split("\\|");
                Book newBook = new Book();
                newBook.setId(Integer.valueOf(req[0]));
                newBook.setTitle(req[1]);
                newBook.setAuthor(req[2]);
                newBook.setPageNumber(Integer.valueOf(req[3]));
                newBook.setBookStatus(BookStatus.valueOf(req[4]));
                if (req.length>5)
                    newBook.setOwner(req[5]);
                else
                    newBook.setOwner("");
                library.add(newBook);
            }

            in.close();
        } catch (IOException e) {
            throw new DAOException("Can't read file", e);
        }
        return library;
    }


    @Override
    public void editBook() throws DAOException {
        try {
            String inputStr = BufferedInputFile.read(path);
            for (int i = 0; i < getLibrary().size(); i++)
                System.out.println(getLibrary().get(i).toString());
                                      /*
            if (status == BookStatus.AVAILABLE)
                inputStr = inputStr.replaceFirst(id+".+\n", "");
            else
                inputStr = inputStr.replaceFirst(book.getStatus().name(), "AVAILABLE");

            FileOutputStream fileOut = new FileOutputStream("Resources/Library.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            System.out.println(BufferedInputFile.read("Resources/Library.txt"));
                                                                         */
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }

    }

    @Override
    public ArrayList<Book> find(String title) throws DAOException {
            ArrayList<Book> result = new ArrayList<Book>();
            for (Book book : getLibrary()) {
                if (book.getTitle().equals(title))
                    result.add(book);
            }
        return result;

    }

     @Override
      public void updateBook(Book book) throws DAOException {
        try {
            String inputStr = BufferedInputFile.read(path);

            inputStr = inputStr.replaceFirst(book.getId() +"\\|"+book.getTitle()+ ".+\n", book.toString()+ "\n");

            FileOutputStream fileOut = new FileOutputStream(path);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }
}
