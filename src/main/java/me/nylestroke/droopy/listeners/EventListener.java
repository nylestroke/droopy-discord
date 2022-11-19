package me.nylestroke.droopy.listeners;

import net.dv8tion.jda.api.events.guild.GuildReadyEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class EventListener extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {

    }

    @Override
    public void onGuildReady(@NotNull GuildReadyEvent event) {

    }

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent event) {

    }
}
