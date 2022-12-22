package me.nylestroke.droopy.tools;

import io.github.cdimascio.dotenv.Dotenv;

public abstract class Configuration {
    private static final Dotenv config = Dotenv.configure().ignoreIfMissing().load();

    public static Dotenv getConfig() { return config; }

    public static String getBotToken() { return config.get("TOKEN"); }

    public static Long getBotServerId() { return Long.parseLong(config.get("SERVER_ID")); }
}
