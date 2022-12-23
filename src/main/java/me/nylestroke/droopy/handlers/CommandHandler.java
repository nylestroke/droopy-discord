package me.nylestroke.droopy.handlers;

import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.Logger;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.ArrayList;
import java.util.List;

public class CommandHandler {
    private static final List<CommandData> commandDataList = new ArrayList<>();
    private static final List<BotCommand> cmdsList = new ArrayList<>();

    public static void addCommand(BotCommand command) {
        if (command.useOptions()) {
            commandDataList.add(
                    Commands.slash(
                            command.getName(),
                            command.getDescription()
                    ).addOptions(command.getOptions())
            );
        } else {
            commandDataList.add(Commands.slash(command.getName(), command.getDescription()));
        }

        cmdsList.add(command);
    }

    public static List<CommandData> getCommandData() {
        return commandDataList;
    }

    public static BotCommand getCommandExec(String commandName) {
        for (BotCommand command : cmdsList) {
            if (command.getName().equalsIgnoreCase(commandName)) return command;
        }
        Logger.info("Cannot find current command in command handler");
        return null;
    }
}
