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

public class randomCmd extends BotCommand {

    public randomCmd() {
        super(
                "random",
                "Generate random number from first value up to second value",
                true,
                new OptionData[] {
                        new OptionData(
                                OptionType.INTEGER,
                                "from",
                                "first value",
                                true
                        ),
                        new OptionData(OptionType.INTEGER,
                                "to",
                                "second value",
                                true
                        )
                }
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        int firstValue = event.getOption("from").getAsInt();
        int secondValue = event.getOption("to").getAsInt();

        Random rand = new Random();
        int generated = rand.nextInt(firstValue, secondValue);
        List<String[]> fields = new ArrayList<>();

        fields.add(new String[]{"First value", String.valueOf(firstValue)});
        fields.add(new String[]{"Second value", String.valueOf(secondValue)});

        MessageEmbed embed = EmbedCreator.createEmbed(
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
