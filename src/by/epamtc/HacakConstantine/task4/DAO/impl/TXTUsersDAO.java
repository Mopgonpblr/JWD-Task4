package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.BufferedInputFile;
import by.epamtc.HacakConstantine.task4.DAO.UsersDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.*;
import java.util.ArrayList;

public class TXTUsersDAO implements UsersDAO {

    final static String path = "Resources/Users.txt";
    @Override
    public void register(User user) throws DAOException {
        try (PrintWriter out = new PrintWriter(
                new BufferedWriter(new FileWriter(path,true)))) {

            out.write(user.toString());
            out.close();
        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }

    @Override
    public User authorize(String login, String password) throws DAOException {
        User u = new User();
        for (User user: getUserBase()){
            if (user.getLogin().equals(login))
                if (user.getPassword().equals(password))             {
                    u.setLogin(user.getLogin());
                    u.setName(user.getName());
                    u.setRole(user.getRole());
                }
                else
                    throw new DAOException("Wrong password");
        }
        return u;
    }

    @Override
    public void signOut(String login) throws DAOException{

    }

    @Override
    public void delete(String login) throws DAOException{
        try {
            String inputStr = BufferedInputFile.read(path);

            inputStr = inputStr.replaceFirst(".+("+login + ").+\n", "");

            FileOutputStream fileOut = new FileOutputStream(path);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            System.out.println(BufferedInputFile.read(path));

        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }

    public ArrayList<User> getUserBase() throws DAOException {

        ArrayList<User> base = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(path));
            String s;
            User user;
            while ((s = in.readLine()) != null) {
                String[] req = s.split("\\|");
                user = new User();
                user.setId(Integer.valueOf(req[0]));
                user.setLogin(req[1]);
                user.setPassword(req[2]);
                user.setName(req[3]);
                user.setRole(Role.valueOf(req[4]));
                base.add(user);
            }
            in.close();
        } catch (IOException e) {
            throw new DAOException("Can't read file", e);
        }
        return base;
    }

}
