package org.doomsdayrs.bots.kensho.commands;/*
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

import de.btobastian.sdcf4j.Command;
import de.btobastian.sdcf4j.CommandExecutor;
import de.btobastian.sdcf4j.CommandHandler;
import org.doomsdayrs.bots.kensho.main.Logs;
import org.javacord.api.DiscordApi;
import org.javacord.api.entity.channel.TextChannel;
import org.javacord.api.entity.server.Server;
import org.javacord.api.entity.user.User;

public class Commands implements CommandExecutor {
    final DiscordApi discordApi;
    final CommandHandler commandHandler;

    public Commands(DiscordApi discordApi, CommandHandler commandHandler) {
        this.discordApi = discordApi;
        this.commandHandler = commandHandler;
    }

    @Command(aliases = {"mute"}, description = "#Amute a user")
    public void onMuteCommand(TextChannel channel, Server server, User user, String command, String victim) {
        Logs.logCommand(channel, user, "mute");
    }

}
