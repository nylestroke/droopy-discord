package me.nylestroke.droopy.commands.utils;

import me.nylestroke.droopy.models.BotCommand;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class clearCmd extends BotCommand {
    public clearCmd() {
        super(
                "clear",
                "Clears specify amount messages from current channel.",
                true,
                new OptionData[] {
                        new OptionData(
                                OptionType.INTEGER,
                                "amount",
                                "count of messages which you want to delete",
                                true
                        )
                }
        );
    }

    @Override
    public void exec(@NotNull SlashCommandInteractionEvent event) {
        int amount = event.getOption("amount").getAsInt();

        List<Message> messages = event.getChannel().getHistory().retrievePast(amount).complete();
        event.getChannel().asGuildMessageChannel().deleteMessages(messages).queue();
        event.reply("You are successfully deleted " + amount + " messages").setEphemeral(true).queue();
    }
}
