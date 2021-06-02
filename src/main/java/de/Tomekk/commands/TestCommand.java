package de.Tomekk.commands;

import de.Tomekk.util.ICommand;
import de.Tomekk.util.ICommandCategory;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.TextChannel;

import javax.annotation.Nullable;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 19:04.
 */
public class TestCommand extends ICommand {

    public TestCommand(  ) {
        super( "test", null, 3, new String[]{"test1", "test2"}, "rtokbnmr\nebjnrth", "", ICommandCategory.NOAH );
    }

    @Override
    public void handle( Member member, TextChannel textChannel, Message rawMessage, String[] args ) {

        if(member.getVoiceState() != null) {

            textChannel.sendMessage( member.getAsMention() + " du stinkst halt." );

        }

    }
}
