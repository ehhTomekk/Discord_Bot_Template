package de.Tomekk.util;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 18:40.
 */
public class Cooldown {

    public String ID, category;

    public Cooldown(String ID, String category) {

        this.ID = ID;
        this.category = category;

    }

    @SuppressWarnings( "deprecation" )
    public void set(EnumTime time, int cooldown) {

        Date date = new Date( );
        Timestamp startingTime = new Timestamp( date.getTime( ) );

        Calendar cal = Calendar.getInstance( );
        cal.setTimeInMillis( startingTime.getTime( ) );

        switch ( time ) {

            case SECOND:
                cal.add( Calendar.SECOND, cooldown );
                break;

            case MINUTE:
                cal.add( Calendar.MINUTE, cooldown );
                break;

            case HOUR:
                cal.add( Calendar.HOUR, cooldown );
                break;

            case DAY:
                cal.add( Calendar.HOUR, cooldown * 24 );
                break;

            default:
                break;

        }

        Timestamp endingTime = new Timestamp( cal.getTime( ).getTime( ) );
        Storage.getStorage().cooldowns.get( this.category ).put( this.ID, endingTime );


    }

    public void reset() {
        Storage.getStorage().cooldowns.get( this.category ).remove( this.ID );
    }

    public long remainingTimeToLong() {

        Timestamp t1 = new Timestamp( System.currentTimeMillis() );
        Timestamp t2 = Storage.getStorage().cooldowns.get( this.category ).get( this.ID );

        return t2.getTime() - t1.getTime();

    }

    public boolean isOnCooldown() {

        if(!Storage.getStorage().cooldowns.get( this.category ).containsKey( this.ID )) return false;

        return Storage.getStorage().cooldowns.get( this.category ).get( this.ID ).getTime() - new Timestamp( new Date( ).getTime( ) ).getTime() > 0;

    }

    public String convertToDaysHoursMinutes(long minutes) {

        int day = (int) TimeUnit.MILLISECONDS.toDays(minutes);
        long hours = TimeUnit.MILLISECONDS.toHours(minutes) - (day *24);
        long minute = TimeUnit.MILLISECONDS.toMinutes(minutes) - (TimeUnit.MILLISECONDS.toHours(minutes)* 60);
        long second = TimeUnit.MILLISECONDS.toSeconds(minutes)
                - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(minutes));

        String result = "";

        if (day != 0){
            result += "" + day;
            if (day == 1){
                result = result + " day ";
            }
            else{
                result = result + " days ";
            }
        }

        if (hours != 0){
            result += "" + hours;

            if (hours == 1){
                result = result + " hour ";
            }
            else{
                result = result + " hours ";
            }
        }

        if (minute != 0){
            result += "" + minute;

            if (minute == 1){
                result = result + " minute ";
            }
            else{
                result = result + " minutes ";
            }
        }

        if (second != 0){
            result += "" + second;

            if (second == 1){
                result = result + " second.";
            }
            else{
                result = result + " seconds.";
            }
        } else result = result + " seconds.";

        return result;
    }

}
