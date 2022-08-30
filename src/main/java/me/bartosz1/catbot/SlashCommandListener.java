package me.bartosz1.catbot;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.util.HashMap;

public class SlashCommandListener extends ListenerAdapter {

    private final HashMap<String, Command> commands = new HashMap<>();

    public void onSlashCommandInteraction(SlashCommandInteractionEvent event) {
        if (commands.containsKey(event.getName())) {
            commands.get(event.getName()).handle(event);
        }
    }

    public void registerCommand(String name, Command command) {
        commands.put(name, command);
    }
}
