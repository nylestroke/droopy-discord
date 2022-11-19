package me.nylestroke.droopy;

import io.github.cdimascio.dotenv.Dotenv;
import me.nylestroke.droopy.listeners.CommandInteraction;
import me.nylestroke.droopy.listeners.EventListener;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;

/**
 * JDA-5.0.0 Droopy Discord Bot.
 * This is main class where we initialize our bot.
 * @author nylestroke
 */
public class DroopyMain {

    /**
     * Loads environment variables and builds the bot shard manager.
     * @throws LoginException occurs when bot token is invalid.
     */
    public DroopyMain() throws LoginException {

        // Load environment variables
        Dotenv config = new Config().getConfig();
        final String token = config.get("TOKEN");

        // Build jda builder
        JDABuilder builder = JDABuilder.createDefault(token);
        builder.setActivity(Activity.watching("In Development"));
        builder.setStatus(OnlineStatus.DO_NOT_DISTURB);
        builder.enableIntents(GatewayIntent.MESSAGE_CONTENT, GatewayIntent.GUILD_EMOJIS_AND_STICKERS, GatewayIntent.GUILD_MEMBERS, GatewayIntent.GUILD_MESSAGES, GatewayIntent.GUILD_PRESENCES, GatewayIntent.GUILD_BANS);

        // ARegister listeners
        builder.addEventListeners(new EventListener(), new CommandInteraction());


        // Build bot with above configuration
        builder.build();
    }

    /**
     * Main method where we start bot.
     */
    public static void main(String[] args) {
        try {
            new DroopyMain();
        } catch (LoginException e) {
            System.out.println("ERROR: Provided bot token is invalid");
        }
    }
}
