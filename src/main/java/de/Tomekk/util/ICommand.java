package de.Tomekk.util;

import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 18:39.
 */
public abstract class ICommand {

    private String command;
    private int cooldown;

    private String reqRole;

    private List<String> aliases;

    String description, usage;
    ICommandCategory category;

    public ICommand( String command, @Nullable String reqRole, int cooldown, String[] aliases, String description, String usage, ICommandCategory category ) {
        this.command = command;
        if(reqRole != null) this.reqRole = reqRole;
        this.cooldown = cooldown;
        this.aliases = Arrays.asList( aliases );
        this.description = description;
        this.usage = usage;
        this.category = category;
    }

    public abstract void handle( Member member, TextChannel textChannel, Message rawMessage, String[] args );

    public int getCooldown( ) {
        return cooldown;
    }

    public List< String > getAliases( ) {
        return aliases;
    }

    public String getReqRole( ) {
        return reqRole;
    }

    public String getCommand( ) {
        return command;
    }

    public String getDescription( ) {
        return description;
    }

    public String getUsage( ) {
        return usage;
    }

    public ICommandCategory getCategory( ) {
        return category;
    }

}
