package de.Tomekk.util;

import de.Tomekk.commands.TestCommand;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 18:42.
 */
public class CommandHandler {

    private static CommandHandler instance;
    public static CommandHandler getInstance() { return instance; }

    public List<ICommand> commands;

    public CommandHandler() {
        instance = this;
        commands = new ArrayList<>(  );
        registerCommands();
    }

    public void registerCommands() {

        add( new TestCommand(  ) );

    }

    public void add(ICommand command) {
        Storage.getStorage().cooldowns.put( command.getCommand(), new HashMap<>(  ) );
        commands.add( command );
    }

    public void remove(ICommand command) {
        Storage.getStorage().cooldowns.remove( command.getCommand() );
        commands.remove( command );
    }

    public List< ICommand > getCommands( ) {
        return commands;
    }

    public ICommand findMatch(String var) {

        for(ICommand command : commands) {
            if(command.getCommand().equalsIgnoreCase( var )) {
                return command;
            } else {
                for(String str : command.getAliases()) {
                    if(str.equalsIgnoreCase( var )) return command;
                }

            }
        }

        return null;

    }

    public boolean exists(String var) {
        for(ICommand command : commands) {

            if(command.getCommand().equalsIgnoreCase( var )) {
                return true;
            } else {
                for(String str : command.getAliases()) {
                    if(str.equalsIgnoreCase( var )) return true;
                }

            }

        }

        return false;
    }

}
