package me.bartosz1.catbot.commands;

import me.bartosz1.catbot.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PingCommand implements Command {

    private static final Logger LOGGER = LoggerFactory.getLogger(PingCommand.class);

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setTitle(":ping_pong: Pong!");
        embed.setColor(0x48fc42);
        long time = System.currentTimeMillis();
        event.getHook().editOriginalEmbeds(embed.build()).queue(response -> {
            long apiPing = System.currentTimeMillis() - time;
            long gatewayPing = response.getJDA().getGatewayPing();
            embed.setTitle(":ping_pong: Pong!");
            embed.setColor(0x48fc42);
            embed.setDescription(":tools: **API: " + apiPing + "ms**\n:left_right_arrow: **Gateway: " + gatewayPing + "ms**");
            response.editMessageEmbeds(embed.build()).queue(resp -> LOGGER.info("U:" + event.getMember().getId() + " CMD: ping ON C:" + event.getChannel().getId() + "/G:" + event.getGuild().getId()));
        });
        embed.clear();
    }
}
