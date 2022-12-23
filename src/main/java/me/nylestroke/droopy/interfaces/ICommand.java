package me.nylestroke.droopy.interfaces;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.jetbrains.annotations.NotNull;

public interface ICommand {
    String getName();
    String getDescription();
    void exec(@NotNull SlashCommandInteractionEvent event);
}
