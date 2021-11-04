package by.epamtc.HacakConstantine.task4.Controller.command;

import by.epamtc.HacakConstantine.task4.Controller.exception.ControllerException;


public interface Command {
    public String execute (String request) throws ControllerException;
}
