package me.nylestroke.droopy.commands.server;

import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.EmbedCreator;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

public class avatarCmd extends BotCommand {

    public avatarCmd() {
        super(
                "avatar",
                "Get avatar from specify member",
                true,
                new OptionData[] {
                        new OptionData(
                                OptionType.USER,
                                "user",
                                "mentioned user",
                                true
                        )
                }
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        Member member = event.getOption("user").getAsMember();
        event.deferReply().setEphemeral(true).queue();

        MessageEmbed embed = EmbedCreator.createEmbed(
                null,
                event.getOption("user").getAsUser().getName() + "'s avatar",
                null,
                null,
                member.getEffectiveAvatarUrl(),
                null,
                null);

        event.getHook().sendMessageEmbeds(embed).queue();
    }
}
