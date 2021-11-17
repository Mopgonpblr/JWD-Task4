package by.epamtc.HacakConstantine.task4.Service.exception;

public class ServiceException extends Exception {
    public ServiceException(String message){
        System.out.println(message);
    }

    public ServiceException(String message, Exception e){
        System.out.println(message+"\n"+e);
    }
}
