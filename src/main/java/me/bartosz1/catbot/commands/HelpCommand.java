package me.bartosz1.catbot.commands;

import me.bartosz1.catbot.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelpCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelpCommand.class);

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle("List of commands");
        embed.addField("Animal pictures", "``/bird`` ``/cat`` ``/dog`` ``/duck`` ``/fox`` ``/owl`` ``/pepsi`` ``/shibe``", false);
        embed.addField("Other", "``/help`` ``/ping``", false);
        event.getHook().editOriginalEmbeds(embed.build()).queue(resp -> LOGGER.info("U:" + event.getMember().getId() + " CMD: help ON C:" + event.getChannel().getId() + "/G:" + event.getGuild().getId()));
    }
}
