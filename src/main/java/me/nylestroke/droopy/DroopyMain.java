package me.nylestroke.droopy;

import me.nylestroke.droopy.listeners.CommandInteraction;
import me.nylestroke.droopy.listeners.EventListener;
import me.nylestroke.droopy.tools.Configuration;
import me.nylestroke.droopy.tools.Logger;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

/**
 * Droopy Discord Bot using JDA 5.0.0-beta.2.
 * @author nylestroke
 */
public class DroopyMain {
    public static void main(String[] args) {
        try {

            // Configure jda builder
            JDABuilder builder = JDABuilder.createDefault(Configuration.getBotToken());
            builder.setActivity(Activity.watching("In Development"));
            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
            builder.enableIntents(
                    GatewayIntent.MESSAGE_CONTENT,
                    GatewayIntent.GUILD_EMOJIS_AND_STICKERS,
                    GatewayIntent.GUILD_MEMBERS,
                    GatewayIntent.GUILD_MESSAGES,
                    GatewayIntent.GUILD_PRESENCES,
                    GatewayIntent.GUILD_BANS
            );

            // Register listeners and handlers
            builder.addEventListeners(
                    new EventListener(),
                    new CommandInteraction()
            );

            // Build and start the bot
            builder.build();

        } catch (Exception exception) {

            // Custom logger message
            Logger.error(exception);
        }
    }
}
