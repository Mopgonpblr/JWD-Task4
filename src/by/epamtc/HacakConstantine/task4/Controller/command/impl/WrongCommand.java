package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;

public class WrongCommand implements Command {
    public String execute (String request){
        return "Wrong command";
    }
}
