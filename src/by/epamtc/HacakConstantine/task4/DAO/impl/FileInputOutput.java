package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.*;

public class FileInputOutput {
    public static String read(String path) throws DAOException {
        String s;
        StringBuilder sb = new StringBuilder();

        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            while ((s = in.readLine()) != null)
                sb.append(s + "\n");
        }catch (IOException e){
            throw new DAOException("File not found", e);
        }
        return sb.toString();
    }


    public static void write(String data,String path, boolean isAppend) throws DAOException{
        try (FileOutputStream out = new FileOutputStream(path, isAppend)) {
            out.write(data.getBytes());
            out.close();
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }
}