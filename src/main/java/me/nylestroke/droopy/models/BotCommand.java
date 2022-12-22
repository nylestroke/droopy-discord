package me.nylestroke.droopy.models;

import me.nylestroke.droopy.interfaces.ICommand;
import me.nylestroke.droopy.interfaces.ICommandOption;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import org.jetbrains.annotations.NotNull;

public class BotCommand implements ICommand, ICommandOption {
    protected String name;
    protected String description;
    protected Boolean useOptions;
    protected String optionName;
    protected String optionDescription;
    protected Boolean optionRequired;
    protected OptionType optionType;

    public BotCommand() { }

    public BotCommand(
            String name,
            String description
    ) {
        this.name = name;
        this.description = description;
    }

    public BotCommand(
            String name,
            String description,
            Boolean useOptions,
            String optionName,
            String optionDescription,
            Boolean optionRequired,
            OptionType optionType
    ) {
        this.name = name;
        this.description = description;
        this.useOptions = useOptions;
        this.optionName =  optionName;
        this.optionDescription = optionDescription;
        this.optionRequired = optionRequired;
        this.optionType = optionType;
    }

    public void exec(@NotNull SlashCommandInteractionEvent event) {}

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public Boolean useOptions() {
        return this.useOptions;
    }

    @Override
    public String optionName() {
        return this.optionName;
    }

    @Override
    public String optionDescription() {
        return this.optionDescription;
    }

    @Override
    public Boolean optionRequired() {
        return this.optionRequired;
    }

    @Override
    public OptionType optionType() {
        return this.optionType;
    }
}
