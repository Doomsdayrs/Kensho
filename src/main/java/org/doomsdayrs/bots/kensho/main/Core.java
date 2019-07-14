package org.doomsdayrs.bots.kensho.main;
/*
 * This file is part of Kensho.
 * Kensho is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * Kensho is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Kensho.  If not, see <https://www.gnu.org/licenses/>.
 * ====================================================================
 * Kensho
 * 03 / 07 / 2019
 *
 * @author github.com/doomsdayrs
 */

import de.btobastian.sdcf4j.CommandHandler;
import de.btobastian.sdcf4j.handler.JavacordHandler;
import org.doomsdayrs.bots.kensho.Version;
import org.doomsdayrs.bots.kensho.support.Config;
import org.doomsdayrs.bots.kensho.support.Others;
import org.doomsdayrs.bots.kensho.support.RunningTime;
import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;
import org.javacord.api.entity.permission.PermissionType;
import org.javacord.api.entity.permission.PermissionsBuilder;

/**
 * Clecia, Coded by Rahim Akhter 2018-19
 */


public class Core {


    //Command executors

    public static String botInvite;
    static DiscordApi API;

    private static boolean running = false;
    private static CommandHandler cmdHandler;


    public static void main(String[] args) {

        running = true;
        System.out.println("\nMy version hun: " + Version.VersionNum);
        System.out.println("Change log: " + "\n==============================" + Version.changeLog);
        System.out.println("Hello " + System.getProperty("user.name") + "!");
        System.out.println("The OS is: " + System.getProperty("os.name") + "," + System.getProperty("os.version") + ", " + System.getProperty("os.arch"));
        System.out.println("Java version: " + System.getProperty("java.version"));
        System.out.println("Current nano time " + RunningTime.startTime);
        System.out.println();


        //The bulk of the program

        API = new DiscordApiBuilder().setToken(Config.token).login().join();
        botInvite = API.createBotInvite(new PermissionsBuilder().setAllowed(PermissionType.EMBED_LINKS, PermissionType.SEND_MESSAGES, PermissionType.READ_MESSAGES, PermissionType.ATTACH_FILE).build());
        cmdHandler = new JavacordHandler(API);

        cmdHandler.setDefaultPrefix(Config.prefix);


        API.updateActivity(ActivityType.WATCHING, Config.prefix + "help");

        System.out.println("\n========= This is the prefix:    " + cmdHandler.getDefaultPrefix());
        System.out.println("\n========= This is an invite link:    " + botInvite + "\n");
        System.out.println(
                "====================================================================" +
                        "\nToken used: " + Config.token +
                        "\nActive Logger?: " + Config.logEnabled);


    }

    public boolean shutdown() {
        if (running) {
            System.out.println("\n shutdown command by (ADMIN) at " + Others.dateFormat.format(Others.date));
            API.disconnect();
            System.out.println("Executed " + Others.commandsExecuted + " commands");

            return true;
        } else {
            System.out.println("Attempt to shutdown bot invalid, Bot is offline");
            return false;
        }
    }
}