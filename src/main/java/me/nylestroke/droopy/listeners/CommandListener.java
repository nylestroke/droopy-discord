package me.nylestroke.droopy.listeners;

import me.nylestroke.droopy.commands.server.avatarCmd;
import me.nylestroke.droopy.commands.server.pingCmd;
import me.nylestroke.droopy.commands.server.rolesCmd;
import me.nylestroke.droopy.commands.utils.clearCmd;
import me.nylestroke.droopy.commands.utils.guessCmd;
import me.nylestroke.droopy.commands.utils.randomCmd;
import me.nylestroke.droopy.commands.utils.userinfoCmd;
import me.nylestroke.droopy.handlers.CommandHandler;
import me.nylestroke.droopy.models.BotCommand;
import me.nylestroke.droopy.tools.Configuration;
import me.nylestroke.droopy.tools.Logger;
import net.dv8tion.jda.api.entities.channel.unions.DefaultGuildChannelUnion;
import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.restaction.CommandListUpdateAction;
import org.jetbrains.annotations.NotNull;

public class CommandListener extends ListenerAdapter {

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {
        long serverId = Configuration.getBotServerId();
        long currentGuild = event.getGuild().getIdLong();
        CommandListUpdateAction updateAction = event.getGuild().updateCommands();
        DefaultGuildChannelUnion defaultChannel = event.getGuild().getDefaultChannel();

        // Adding commands
        CommandHandler.addCommand(new userinfoCmd());
        CommandHandler.addCommand(new rolesCmd());
        CommandHandler.addCommand(new clearCmd());
        CommandHandler.addCommand(new avatarCmd());
        CommandHandler.addCommand(new pingCmd());
        CommandHandler.addCommand(new guessCmd());
        CommandHandler.addCommand(new randomCmd());

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
        BotCommand cmd = CommandHandler.getCommandExec(command);

        try {
            cmd.exec(event);
            Logger.successful(cmd.getName() + " successfully executed!");
        } catch (Exception exception) {
            Logger.error(exception);
        } finally {
            Logger.info("Command " + cmd.getName() + " executed.");
        }
    }
}
