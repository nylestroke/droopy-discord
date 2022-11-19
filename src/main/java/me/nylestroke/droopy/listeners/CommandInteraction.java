package me.nylestroke.droopy.listeners;

import io.github.cdimascio.dotenv.Dotenv;
import me.nylestroke.droopy.Config;
import me.nylestroke.droopy.DroopyMain;
import me.nylestroke.droopy.EmbedCreator;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;

import javax.security.auth.login.LoginException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CommandInteraction extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        List<CommandData> commandData = new ArrayList<>();
        Dotenv config = new Config().getConfig();
        long serverId = Long.parseLong(config.get("SERVER_ID"));

        commandData.add(Commands.slash("roles", "Get all roles from current guild."));
        commandData.add(Commands.slash("clear", "Clears specify amount messages from current channel.")
                .addOption(OptionType.INTEGER, "amount", "count of messages which you want to delete", true));
        commandData.add(Commands.slash("userinfo", "Get information about specify user.")
                .addOption(OptionType.USER, "user", "mentioned user", true));
        commandData.add(Commands.slash("avatar", "Get avatar from specify member")
                .addOption(OptionType.USER, "user", "mentioned user", true));
        commandData.add(Commands.slash("ping", "Get yours client ping"));
        commandData.add(Commands.slash("guess", "Guess the number from 1 up to 10")
                .addOption(OptionType.INTEGER, "number", "guess number", true));
        commandData.add(Commands.slash("random", "Generate random number from first value up to second value")
                .addOption(OptionType.INTEGER, "from", "first value", true)
                .addOption(OptionType.INTEGER, "to", "second value", true));

        if (event.getGuild().getIdLong() == serverId) {
            event.getGuild().updateCommands().addCommands(commandData).queue();
        } else {
            event.getGuild().getDefaultChannel().asStandardGuildMessageChannel()
                    .sendMessage("There is no Droopy Discord Server. Bot commands are unavailable").queue();
        }
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        // Setup local variables
        String command = event.getName();
        ArrayList<String[]> fields = new ArrayList<>();
        MessageEmbed embed;

        /**
         * @description list of all roles in current guild
         * @include embedCreator
         */
        if (command.equals("roles")) {
            event.deferReply().setEphemeral(true).queue();
            String response = "";
            for (Role role : event.getGuild().getRoles()) {
                if (role.getAsMention() != "@everyone") {
                    response += role.getAsMention() + " - Access level: " + role.getPosition() + "\n";
                }
            }

            embed = new EmbedCreator().createEmbed(
                    event.getGuild().getName() + " roles",
                    null,
                    null,
                    event.getGuild().getIconUrl(),
                    null,
                    null,
                    response);
            event.getHook().sendMessageEmbeds(embed).queue();
        }
        /**
         * @description clear's specify amount of message in current channel
         * @include embedCreator
         */
        else if (command.equals("clear")) {
            Integer amount = event.getOption("amount").getAsInt();
            List<Message> messages = event.getChannel().getHistory().retrievePast(amount).complete();
            event.getChannel().asGuildMessageChannel().deleteMessages(messages).queue();
            event.reply("You are successfully deleted " + amount + " messages").setEphemeral(true).queue();

        }
        /**
         * @description return's information about specify guild member
         * @include embedCreator
         */
        else if (command.equals("userinfo")) {
            Member member = event.getOption("user").getAsMember();
            User user = event.getOption("user").getAsUser();
            event.deferReply().queue();
            String memberRoles = "";

            fields.add(new String[]{"UserID", member.getId()});

            try {
                List<Role> unsortedRoles = event.getGuild().getMemberById(member.getId()).getRoles();
                for (Role role : unsortedRoles) {
                    memberRoles += role.getAsMention();
                }
            } catch (Exception e) {
                event.reply("Some error corrupted when trying to execute this command");
            }
            if (memberRoles.length() > 0) {
                fields.add(new String[]{"Roles", memberRoles});
            }

            fields.add(new String[]{"Server Member Since", member.getTimeJoined().format(DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm:ss"))});
            fields.add(new String[]{"Discord Member Since", user.getTimeCreated().format(DateTimeFormatter.ofPattern("MMMM dd yyyy HH:mm:ss"))});

            embed = new EmbedCreator().createEmbed(
                    user.isBot() ? "Bot Information" : null,
                    user.getName(),
                    user.getEffectiveAvatarUrl(),
                    member.getEffectiveAvatarUrl(),
                    null,
                    fields,
                    null);

            event.getHook().sendMessageEmbeds(embed).setEphemeral(true).queue();
        }
        /**
         * @description return's the avatar of mentioned member
         * @include embedCreator
         */
        else if (command.equals("avatar")) {
            Member member = event.getOption("user").getAsMember();
            event.deferReply().setEphemeral(true).queue();

            embed = new EmbedCreator().createEmbed(
                    null,
                    event.getOption("user").getAsUser().getName() + "'s avatar",
                    null,
                    null,
                    member.getEffectiveAvatarUrl(),
                    null,
                    null);

            event.getHook().sendMessageEmbeds(embed).queue();
        }
        /**
         * @description return's current ping of member
         * @include embedCreator
         */
        else if (command.equals("ping")) {
            event.deferReply().setEphemeral(true).queue();
            embed = new EmbedCreator().createEmbed(
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
        /**
         * @description return random number from 1 up to 10
         * @include embedCreator
         */
        else if (command.equals("guess")) {
            Integer number = event.getOption("number").getAsInt();
            Random rand = new Random();
            Integer generated = rand.nextInt(10);
            MessageEmbed winEmbed;
            fields.clear();

            fields.add(new String[]{"Your number", number.toString()});
            fields.add(new String[]{"Generated number", generated.toString()});

            embed = new EmbedCreator().createEmbed(
                    number.equals(generated) ? "🟢 You guessed the number!" : "🔴 You didn't guess",
                    null,
                    null,
                    null,
                    null,
                    fields,
                    null);

            if (number.equals(generated)) {
                winEmbed = new EmbedCreator().createEmbed(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        "🎉 Congratulations! Member - " + event.getUser().getName() + " guessed the number! Added 5 coins to wallet.");

                event.replyEmbeds(embed, winEmbed).setEphemeral(true).queue();
            } else {
                event.replyEmbeds(embed).setEphemeral(true).queue();
            }

        }
        /**
         * @description generate a random number from first value up to second value
         * @include embedCreator
         */
        else if (command.equals("random")) {
            Integer firstValue = event.getOption("from").getAsInt();
            Integer secondValue = event.getOption("to").getAsInt();
            Random rand = new Random();
            int generated = rand.nextInt(firstValue, secondValue);

            fields.clear();
            fields.add(new String[]{"First value", firstValue.toString()});
            fields.add(new String[]{"Second value", secondValue.toString()});

            embed = new EmbedCreator().createEmbed(
                    "Generate random number",
                    null,
                    null,
                    null,
                    null,
                    fields,
                    "🧬 Your generated number: **" + generated + "**"
            );

            event.replyEmbeds(embed).setEphemeral(true).queue();
        }
    }
}
