package de.Tomekk.events;

import de.Tomekk.util.*;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 18:38.
 */
public class GuildMessageReceived extends ListenerAdapter {

    public void onGuildMessageReceived( GuildMessageReceivedEvent event ) {

        // Dies fragt ab ob der User von dem eine neue Nachricht ausgeht ein Bot ist, wenn ja hört die Methode hier auf da
        // nur normale Nutzer den Bot nutzen sollen.
        if(event.getAuthor().isBot()) return;

        // Recht offensichtlich, aber dies fragt ab ob die Nachricht mit dem von dir in der Storage Klasse angegebenen
        // Prefix anfängt.
        if(event.getMessage().getContentRaw().startsWith( Storage.getStorage().PREFIX )) {

            CommandHandler handler = Storage.getStorage().commandHandler;
            String command = event.getMessage().getContentRaw().substring( Storage.getStorage().PREFIX.length() ).split( " " )[0];

            if(handler.exists( command )) {

                ICommand execCommand = handler.findMatch( command );
                Cooldown cooldown = new Cooldown( event.getAuthor().getId(), execCommand.getCommand() );

                // Fragt ab, ob der User einen laufenden Cooldown auf dem Command hat, wenn ja, wird ihm bescheid gegeben.
                if(cooldown.isOnCooldown()) {

                    event.getChannel().sendMessage( new EmbedBuilder()
                            .setTitle( "On cooldown!" )
                            .setDescription( "Sorry, but youre still on cooldown for this command! " + cooldown.convertToDaysHoursMinutes( cooldown.remainingTimeToLong() ) + " left." ).build() ).queue();

                    return;
                }

                String[] rawArgs = event.getMessage().getContentRaw().split( " " );

                if(rawArgs.length >= 2) {

                    String str1 = event.getMessage( ).getContentRaw( ).substring( Storage.getStorage( ).PREFIX.length( ) + command.length( ) + 1 );
                    String[] args = str1.split( " " );

                    handler.findMatch( command ).handle( event.getMember( ), event.getChannel( ), event.getMessage( ), args );
                    if(execCommand.getCooldown() != 0) cooldown.set( EnumTime.SECOND, execCommand.getCooldown() );

                } else {

                    handler.findMatch( command ).handle( event.getMember( ), event.getChannel( ), event.getMessage( ), new String[]{} );
                    if(execCommand.getCooldown() != 0) cooldown.set( EnumTime.SECOND, execCommand.getCooldown() );
                }

            }

        }

    }

}
