package me.nylestroke.droopy;

import io.github.cdimascio.dotenv.Dotenv;

public class Config {
    private Dotenv config = Dotenv.configure().ignoreIfMissing().load();;

    /**
     * Retrieves the bot config to access environment variables.
     * @return the Dotenv instance for the bot.
     */
    public Dotenv getConfig() {
        return config;
    }

}
