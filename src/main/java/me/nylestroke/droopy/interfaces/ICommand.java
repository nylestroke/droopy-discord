package me.nylestroke.droopy.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface ICommand {
    String getName();
    String getDescription();
    void exec(@NotNull SlashCommandInteractionEvent event);
}
