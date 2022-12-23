package me.nylestroke.droopy.models;

import me.nylestroke.droopy.interfaces.ICommand;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import org.jetbrains.annotations.NotNull;

public class BotCommand implements ICommand {
    private String name;
    private String description;
    private Boolean useOptions;
    private OptionData[] options;

    public BotCommand() { }

    public BotCommand(
            String name,
            String description,
            Boolean useOptions,
            OptionData[] options
    ) {
        this.name = name;
        this.description = description;
        this.useOptions = useOptions;
        this.options = options;
    }

    public void exec(@NotNull SlashCommandInteractionEvent event) {}

    @Override
    public String getName() { return this.name; }

    @Override
    public String getDescription() { return this.description; }

    public OptionData[] getOptions() { return this.options; }

    public Boolean useOptions() { return this.useOptions; }
}
