package me.nylestroke.droopy.commands.utils;

import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.EmbedCreator;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class guessCmd extends BotCommand {

    public guessCmd() {
        super(
                "guess",
                "Simple guess a number game from 1 up to 10",
                true,
                new OptionData[] {
                        new OptionData(
                                OptionType.INTEGER,
                                "number",
                                "guess number",
                                true
                        )
                }
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        int number = event.getOption("number").getAsInt();

        Random rand = new Random();
        int generated = rand.nextInt(1, 10);
        List<String[]> fields = new ArrayList<>();

        fields.add(new String[]{"Your number", String.valueOf(number)});
        fields.add(new String[]{"Generated number", String.valueOf(generated)});

        MessageEmbed embed = EmbedCreator.createEmbed(
                number == generated ? "🟢 You guessed the number!" : "🔴 You didn't guess",
                null,
                null,
                null,
                null,
                fields,
                null);

        if (number == generated) {
            MessageEmbed winEmbed = EmbedCreator.createEmbed(
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

}
