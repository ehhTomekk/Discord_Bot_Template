package de.Tomekk.util;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Tomekk.
 * created on 02.06.2021 / 18:30.
 */
public class Storage {

    /**
     *
     * In dieser Klasse kannst du dir sorglos alle möglichen Dinge erstellen und dann in jeder anderen Klasse darauf zugreifen.
     *
     */

    private static Storage instance;
    public static Storage getStorage() { return instance; }

    public CommandHandler commandHandler;

    public Map<String, Map<String, Timestamp > > cooldowns;

    public Storage() {
        instance = this;
        cooldowns = new HashMap<>(  );
        commandHandler = new CommandHandler();
    }

    /**
     *
     * Ein String ist eine Zeichenkette. Beispiel: "ABC123 was geht ab".
     * Ein Integer ist eine volle Zahl, bsp. 1, 2 oder 3.
     * Ein Boolean ist ein Objekt mit 2 Zuständen, true oder false.
     * Ein Double ist eine Zahl mit Nachkommastellen. Bsp: 42.8D. Das "D" wird mit angegeben da es noch weitere variablen gibt,
     * welche Nachkommastellen haben (Float)
     * Ein Float ähnlich aufgebaut, allerdings ist ein Double doppelt so groß wie ein Float, also präziser.
     *
     * "public" bedeutet, dass wenn man auf die Inhalten dieser Klasse zugreift, man auf dieses Objekt zugreifen kann, um es simpel auszudrücken.
     * Demnach kannst du es also auch auf "private" umstellen und dann wird man diese Variable eben nicht erreichen können.
     *
     */

    public String PREFIX = "a!";
    public String VERSION = "1.0.0";


}
