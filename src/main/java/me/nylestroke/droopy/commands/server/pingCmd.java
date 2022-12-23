package me.nylestroke.droopy.commands.server;

import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.EmbedCreator;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class pingCmd extends BotCommand {

    public pingCmd() {
        super(
                "ping",
                "Get yours client ping",
                false,
                null
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        event.deferReply().setEphemeral(true).queue();

        MessageEmbed embed = EmbedCreator.createEmbed(
                null,
                null,
                null,
                null,
                null,
                null,
                "🏓 Ping: " + event.getJDA().getGatewayPing() + "ms"
        );

        event.getHook().sendMessageEmbeds(embed).queue();
    }
}
