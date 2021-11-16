package by.epamtc.HacakConstantine.task4.Controller;

import by.epamtc.HacakConstantine.task4.Controller.command.*;
import by.epamtc.HacakConstantine.task4.Controller.command.impl.*;

import java.util.HashMap;
import java.util.Map;


final class CommandProvider {
    private final Map<CommandName, Command> rep = new HashMap<CommandName, Command>();

    CommandProvider() {
        rep.put(CommandName.SIGN_IN, new SignIn());
        rep.put(CommandName.REGISTER, new Register());
        rep.put(CommandName.DELETE_USER, new DeleteUser());
        rep.put(CommandName.ADD_BOOK, new AddBook());
        rep.put(CommandName.REMOVE_BOOK, new RemoveBook());
        rep.put(CommandName.FIND_BOOK, new FindBook());
        rep.put(CommandName.SHOW_BOOKS, new ShowBooks());
        rep.put(CommandName.TAKE_BOOK, new TakeBook());
        rep.put(CommandName.GIVE_BOOK, new GiveBook());
        rep.put(CommandName.CLOSE, new Close());
        rep.put(CommandName.WRONG, new WrongCommand());
    }

    Command getCommand(String name){
        CommandName commandName = null;
        Command command = null;

        try {
            commandName = CommandName.valueOf(name.toUpperCase());
            command = rep.get(commandName);
        }catch (IllegalArgumentException | NullPointerException e){
            command = rep.get(CommandName.WRONG);
        }
        return command;
    }

    public void addCommand(CommandName cn,Command c){
        rep.put(cn,c);
    }
}
