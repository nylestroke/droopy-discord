package me.nylestroke.droopy.commands.server;

import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.EmbedCreator;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public class rolesCmd extends BotCommand {
    public rolesCmd() {
        super(
                "roles",
                "Get list of all roles from current guild",
                false,
                null
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        event.deferReply().setEphemeral(true).queue();

        String response = "";

        for (Role role : event.getGuild().getRoles()) {
            if (!role.getAsMention().equals("@everyone")) {
                response += role.getAsMention() + " - Access level: " + role.getPosition() + "\n";
            }
        }

        MessageEmbed embed = EmbedCreator.createEmbed(
                event.getGuild().getName() + " roles",
                null,
                null,
                event.getGuild().getIconUrl(),
                null,
                null,
                response.toString());

        event.getHook().sendMessageEmbeds(embed).queue();
    }
}
