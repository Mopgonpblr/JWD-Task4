package by.epamtc.HacakConstantine.task4.DAO.exception;

public class DAOException extends Exception {

    public DAOException(String message, Exception e){
        System.out.println(message);
    }

    public DAOException(String message){
        System.out.println(message);
    }
}
