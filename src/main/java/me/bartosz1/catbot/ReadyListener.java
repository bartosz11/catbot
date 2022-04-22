package me.bartosz1.catbot;

import me.bartosz1.catbot.commands.*;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;

import java.util.Objects;

public class ReadyListener extends ListenerAdapter {

    private final CatBot catbot;
    private final SlashCommandListener cmdMgr;

    public ReadyListener(CatBot catbot, SlashCommandListener cmdMgr) {
        this.catbot = catbot;
        this.cmdMgr = cmdMgr;
    }

    @Override
    public void onReady(ReadyEvent event) {
        catbot.getBot().getShards().get(0).updateCommands()
                .addCommands(Commands.slash("cat","Get a cat picture."))
                .addCommands(Commands.slash("dog","Get a dog picture."))
                .addCommands(Commands.slash("fox","Get a fox picture."))
                .addCommands(Commands.slash("owl","Get an owl picture."))
                .addCommands(Commands.slash("bird","Get a bird picture."))
                .addCommands(Commands.slash("duck","Get a duck picture."))
                .addCommands(Commands.slash("pepsi","Check out shadowcat.club!"))
                .addCommands(Commands.slash("shibe","Get a shibe picture."))
                .queue();

        cmdMgr.registerCommand("cat", new CatCommand());
        cmdMgr.registerCommand("dog", new DogCommand());
        cmdMgr.registerCommand("fox", new FoxCommand());
        cmdMgr.registerCommand("owl", new OwlCommand());
        cmdMgr.registerCommand("bird", new BirdCommand());
        cmdMgr.registerCommand("shibe", new ShibeCommand());
        cmdMgr.registerCommand("pepsi", new PepsiCommand());
        cmdMgr.registerCommand("duck", new DuckCommand());
    }
}
