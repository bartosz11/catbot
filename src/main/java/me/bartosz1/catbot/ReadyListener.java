package me.bartosz1.catbot;

import me.bartosz1.catbot.commands.*;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ReadyListener extends ListenerAdapter {

    private final CatBot catbot;
    private final SlashCommandListener cmdMgr;
    private static final Logger LOGGER = LoggerFactory.getLogger(ReadyListener.class);
    private static final ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);

    public ReadyListener(CatBot catbot, SlashCommandListener cmdMgr) {
        this.catbot = catbot;
        this.cmdMgr = cmdMgr;
    }

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        catbot.getBot().getShards().get(0).updateCommands()
                .addCommands(Commands.slash("cat", "Get a cat picture."))
                .addCommands(Commands.slash("dog", "Get a dog picture."))
                .addCommands(Commands.slash("fox", "Get a fox picture."))
                .addCommands(Commands.slash("owl", "Get an owl picture."))
                .addCommands(Commands.slash("bird", "Get a bird picture."))
                .addCommands(Commands.slash("duck", "Get a duck picture."))
                .addCommands(Commands.slash("pepsi", "Check out pepsi.pics!"))
                .addCommands(Commands.slash("shibe", "Get a shibe picture."))
                .addCommands(Commands.slash("ping", "Pong!"))
                .addCommands(Commands.slash("help", "Show list of commands"))
                .queue();
        cmdMgr.registerCommand("cat", new CatCommand());
        cmdMgr.registerCommand("dog", new DogCommand());
        cmdMgr.registerCommand("fox", new FoxCommand());
        cmdMgr.registerCommand("owl", new OwlCommand());
        cmdMgr.registerCommand("bird", new BirdCommand());
        cmdMgr.registerCommand("shibe", new ShibeCommand());
        cmdMgr.registerCommand("pepsi", new PepsiCommand());
        cmdMgr.registerCommand("duck", new DuckCommand());
        cmdMgr.registerCommand("ping", new PingCommand());
        cmdMgr.registerCommand("help", new HelpCommand());
        executorService.scheduleAtFixedRate(new PresenceUpdater(catbot.getBot()), 1, 60, TimeUnit.SECONDS);
        LOGGER.info("Bot is ready!");
    }
}
