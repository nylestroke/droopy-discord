package me.nylestroke.droopy.commands.utils;

import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.EmbedCreator;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.entities.User;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class userinfoCmd extends BotCommand {
    public userinfoCmd() {
        super(
                "userinfo",
                "Get information about specify user.",
                true,
                new OptionData[] {
                        new OptionData(
                                OptionType.USER,
                                "target",
                                "Target mentioned user",
                                true
                        )
                }
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        User user = event.getOption("target").getAsUser();
        Member member = event.getOption("target").getAsMember();
        event.deferReply().queue();

        ArrayList<String[]> embedFields = new ArrayList<>();
        String memberRoles = "";

        embedFields.add(new String[]{"UserID", user.getId()});

        try {
            List<Role> unsortedRoles = event.getGuild().getMemberById(user.getId()).getRoles();
            for (Role role : unsortedRoles) {
                memberRoles += role.getAsMention();
            }
        } catch (Exception e) {
            event.reply("Some error corrupted when trying to execute this command");
        }
        if (memberRoles.length() > 0) {
            embedFields.add(new String[]{"Roles", memberRoles});
        }

        embedFields.add(new String[]{"Server Member Since", member.getTimeJoined().format(DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm:ss"))});
        embedFields.add(new String[]{"Discord Member Since", user.getTimeCreated().format(DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm:ss"))});

        MessageEmbed embed = EmbedCreator.createEmbed(
                user.isBot() ? "Bot Information" : null,
                user.getName(),
                user.getEffectiveAvatarUrl(),
                user.getEffectiveAvatarUrl(),
                null,
                embedFields,
                null);

        event.getHook().sendMessageEmbeds(embed).setEphemeral(true).queue();
    }
}
