package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.Bean.enums.Role;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.DAO.UsersDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.*;
import java.util.ArrayList;

public class TXTUsersDAO implements UsersDAO {
    private static final String paramDelimiter = "|";
    private static final String lineEnd = ".+\n";
    private static final String allLine = ".+";
    final static String path = "Resources/Users.txt";

    @Override
    public void registerUser(User user) throws DAOException {
        try {
            FileInputOutput.write(new StringBuilder().append(user.toString()).append(user.getPassword()).append('\n').toString(), path, true);
        } catch (DAOException e) {
            throw new DAOException("File not found", e);
        }
    }

    @Override
    public void deleteUser(String login) throws DAOException {
        try {
            String inputStr = FileInputOutput.read(path);
            inputStr = inputStr.replaceFirst(allLine + '(' + login + ')' + lineEnd, "");
            FileInputOutput.write(inputStr, path, false);
        } catch (DAOException e) {
            throw new DAOException("File not found", e);
        }
    }

    @Override
    public ArrayList<User> loadUserBase() throws DAOException {

        ArrayList<User> base = new ArrayList<>();



        try (BufferedReader in = new BufferedReader(new FileReader(path))) {
            String s;
            User user;
            while ((s = in.readLine()) != null) {
                String[] req = s.split("\\" + paramDelimiter);
                user = new User();
                user.setId(Integer.valueOf(req[0]));
                user.setLogin(req[1]);
                user.setName(req[2]);
                user.setRole(Role.valueOf(req[3]));
                user.setPassword(req[4]);
                base.add(user);
            }
        } catch (IOException e) {
            throw new DAOException("Can't read file", e);
        }
        return base;
    }

}
