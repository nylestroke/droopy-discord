package me.nylestroke.droopy.interfaces;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public interface ICommandOption {
    Boolean useOptions();
    String optionName();
    String optionDescription();
    Boolean optionRequired();
    OptionType optionType();
}
