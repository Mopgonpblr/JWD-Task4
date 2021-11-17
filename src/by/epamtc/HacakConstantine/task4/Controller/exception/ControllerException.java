package by.epamtc.HacakConstantine.task4.Controller.exception;

public class ControllerException extends Exception {
    public ControllerException(String message, Exception e){
        System.out.println(message+"\n"+e);
    }
    public ControllerException(String message){
        System.out.println(message);
    }
}
