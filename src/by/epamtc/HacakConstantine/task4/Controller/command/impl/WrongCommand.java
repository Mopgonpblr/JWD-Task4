package by.epamtc.HacakConstantine.task4.Controller.command.impl;

import by.epamtc.HacakConstantine.task4.Controller.command.Command;

public class WrongCommand implements Command {
    final static String success = "Wrong command";
    @Override
    public String execute (String request){
        return success;
    }
}
