package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.Bean.Book;
import by.epamtc.HacakConstantine.task4.Bean.BookStatus;
import by.epamtc.HacakConstantine.task4.BufferedInputFile;
import by.epamtc.HacakConstantine.task4.DAO.BooksDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.*;
import java.util.*;

public class TXTBooksDAO implements BooksDAO {

    @Override
    public void addBook(Book book) throws DAOException {

        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(path())))) {

            out.write(book.toString());
            out.close();
            System.out.println(BufferedInputFile.read(path()));
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }

    @Override
    public void removeBook(int id) throws DAOException {
        try {
            String inputStr = BufferedInputFile.read("Resources/Library.txt");

            inputStr = inputStr.replaceFirst(id + ".+\n", "");

            FileOutputStream fileOut = new FileOutputStream("Resources/Library.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            //System.out.println(BufferedInputFile.read(path()));
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }


    public ArrayList<Book> bookBase() throws DAOException {

        ArrayList<Book> library = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(path()));
            String s;
            while ((s = in.readLine()) != null) {
                String[] req = s.split("\\|");
                Book newBook = new Book();
                newBook.setId(Integer.valueOf(req[0]));
                newBook.setAuthor(req[1]);
                newBook.setTitle(req[2]);
                newBook.setPageNumber(Integer.valueOf(req[3]));
                newBook.setBookStatus(BookStatus.valueOf(req[4]));
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
            String inputStr = BufferedInputFile.read(path());
            ArrayList<Book> library = bookBase();
            for (int i = 0; i < library.size(); i++)
                System.out.println(library.get(i).toString());
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
            ArrayList<Book> library = bookBase();
            ArrayList<Book> result = new ArrayList<Book>();
            for (Book book : library) {
                if (book.getTitle().equals(title))
                    result.add(book);
            }
        return result;

    }

    public String path(){
        return "Resources/Library.txt";
    }
}
