package by.epamtc.HacakConstantine.task4.DAO.impl;

import by.epamtc.HacakConstantine.task4.Bean.CurrentUser;
import by.epamtc.HacakConstantine.task4.Bean.Role;
import by.epamtc.HacakConstantine.task4.Bean.User;
import by.epamtc.HacakConstantine.task4.Bean.UserStatus;
import by.epamtc.HacakConstantine.task4.BufferedInputFile;
import by.epamtc.HacakConstantine.task4.DAO.UsersDAO;
import by.epamtc.HacakConstantine.task4.DAO.exception.DAOException;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class TXTUsersDAO implements UsersDAO {
    @Override
    public void register(User user) throws DAOException {

    }

    @Override
    public void authorize(String login, String password) throws DAOException {
        ArrayList<User> base = userBase();
        for (User user: base){
            if (user.getLogin().equals(login))
                if (user.getPassword().equals(password))             {
                    CurrentUser.getInstance().setLogin(user.getLogin());
                    CurrentUser.getInstance().setName(user.getName());
                    CurrentUser.getInstance().setRole(user.getRole());
                    CurrentUser.getInstance().setStatus(UserStatus.ONLINE);
                }
                else
                    throw new DAOException("Wrong password");
        }
    }

    @Override
    public void signOut(String login) throws DAOException{

    }

    @Override
    public void delete(String login) throws DAOException{
        try {
            String inputStr = BufferedInputFile.read(path());

            inputStr = inputStr.replaceFirst(".+("+login + ").+\n", "");

            FileOutputStream fileOut = new FileOutputStream("Resources/Users.txt");
            fileOut.write(inputStr.getBytes());
            fileOut.close();
            System.out.println(BufferedInputFile.read(path()));

        } catch (IOException e) {
            throw new DAOException("File not found", e);
        }
    }

    public ArrayList<User> userBase() throws DAOException {

        ArrayList<User> base = new ArrayList<>();

        try {
            BufferedReader in = new BufferedReader(
                    new FileReader(path()));
            String s;
            while ((s = in.readLine()) != null) {
                String[] req = s.split("\\|");
                User user = new User();
                user.setId(Integer.valueOf(req[0]));
                user.setLogin(req[1]);
                user.setPassword(req[2]);
                user.setRole(Role.valueOf(req[3]));
                user.setName(req[4]);
                base.add(user);
            }

            in.close();
        } catch (IOException e) {
            throw new DAOException("Can't read file", e);
        }
        return base;
    }
    public String path(){
        return "Resources/Users.txt";
    }
}
