package me.nylestroke.droopy.listeners;

import me.nylestroke.droopy.commands.userinfoCmd;
import me.nylestroke.droopy.handlers.CommandHandler;
import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.Configuration;
import me.nylestroke.droopy.tools.Logger;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

public class CommandInteraction extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        long serverId = Configuration.getBotServerId();
        long currentGuild = event.getGuild().getIdLong();
        CommandListUpdateAction updateAction = event.getGuild().updateCommands();
        DefaultGuildChannelUnion defaultChannel = event.getGuild().getDefaultChannel();

        CommandHandler.addCommand(new userinfoCmd());

        if (!(currentGuild == serverId)) {
            defaultChannel.asStandardGuildMessageChannel()
                    .sendMessage("There is no Droopy Discord Server. Bot commands are unavailable").queue();
            return;
        }

        updateAction.addCommands(CommandHandler.getCommandData()).queue();
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        String command = event.getName();

        for (CommandData botCmd : CommandHandler.getCommandData()) {
            if (command.equalsIgnoreCase(botCmd.getName())) {
                BotCommand cmd = CommandHandler.getCommandExec(botCmd.getName());
                try {
                    cmd.exec(event);
                } catch (Exception exception) {
                    Logger.error(exception);
                } finally {
                    Logger.info("Command " + cmd.getName() + " executed.");
                }
            }
        }
    }
}
