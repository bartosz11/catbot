package me.bartosz1.catbot.commands;

import me.bartosz1.catbot.CatBot;
import me.bartosz1.catbot.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class DuckCommand implements Command {

    private final Request req = new Request.Builder().url("https://random-d.uk/api/v2/random").build();
    private static final Logger LOGGER = LoggerFactory.getLogger(DuckCommand.class);

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0x453a59);
        try {
            Response resp = CatBot.http.newCall(req).execute();
            DataObject data = DataObject.fromJson(resp.body().string());
            embed.setTitle("Duck");
            embed.setFooter("Powered by random-d.uk");
            embed.setImage(data.getString("url"));
        } catch (IOException e) {
            embed.addField("Error", "No duck pic this time, sorry.", false);
            e.printStackTrace();
        }
        event.getHook().editOriginalEmbeds(embed.build()).queue(resp -> LOGGER.info("U:" + event.getMember().getId() + " CMD: duck ON C:" + event.getChannel().getId() + "/G:" + event.getGuild().getId()));
        embed.clear();
    }
}
