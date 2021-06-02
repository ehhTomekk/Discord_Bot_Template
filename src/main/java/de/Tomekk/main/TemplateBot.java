package de.Tomekk.main;

import de.Tomekk.events.GuildMessageReceived;
import de.Tomekk.util.Storage;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import javax.security.auth.login.LoginException;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 18:28.
 */
public class TemplateBot {

    public static JDABuilder jdaBuilder;
    public static JDA jda;

    /**
     * Das ist die Main Methode, diese wird beim builden ausgeführt, hier startest du alles relevante und "registrierst" es.
     * @param args
     * @throws LoginException
     */

    public static void main(String[] args) throws LoginException {

        jdaBuilder = JDABuilder.createDefault( "DEIN_TOKEN" );
        new Storage();

        jdaBuilder.addEventListeners( new GuildMessageReceived() );

        jdaBuilder.disableCache( CacheFlag.MEMBER_OVERRIDES);

        jdaBuilder.setActivity( Activity.listening( Storage.getStorage().PREFIX + " - " + Storage.getStorage().PREFIX + "help" ));
        jdaBuilder.setStatus( OnlineStatus.IDLE );

        jda = jdaBuilder.build();

    }

}
