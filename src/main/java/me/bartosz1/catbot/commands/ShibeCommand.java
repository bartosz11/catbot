package me.bartosz1.catbot.commands;

import me.bartosz1.catbot.CatBot;
import me.bartosz1.catbot.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.data.DataArray;
import okhttp3.Request;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class ShibeCommand implements Command {

    private final Request req = new Request.Builder().url("https://shibe.online/api/shibes?count=1&urls=true&httpsUrls=true").build();
    private static final Logger LOGGER = LoggerFactory.getLogger(ShibeCommand.class);

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0x453a59);
        try {
            Response resp = CatBot.http.newCall(req).execute();
            DataArray data = DataArray.fromJson(resp.body().string());
            embed.setTitle("Shibe");
            embed.setFooter("Powered by shibe.online");
            embed.setImage(data.getString(0));
        } catch (IOException e) {
            embed.addField("Error", "No shibe pic this time, sorry.", false);
            e.printStackTrace();
        }
        event.getHook().editOriginalEmbeds(embed.build()).queue(resp -> LOGGER.info("U:"+event.getMember().getId()+" CMD: shibe ON C:"+event.getChannel().getId()+"/G:"+event.getGuild().getId()));
        embed.clear();
    }
}
