package me.bartosz1.catbot.commands;

import me.bartosz1.catbot.CatBot;
import me.bartosz1.catbot.Command;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.utils.data.DataObject;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

public class PepsiCommand implements Command {

    private final Request req = new Request.Builder().url("https://api.shadowcat.club/pic.php").build();

    @Override
    public void handle(SlashCommandInteractionEvent event) {
        event.deferReply().queue();
        EmbedBuilder embed = new EmbedBuilder();
        embed.setColor(0x453a59);
        try {
            Response resp = CatBot.http.newCall(req).execute();
            embed.setTitle("Pepsi");
            embed.setFooter("Powered by shadowcat.club");
            embed.setImage(resp.body().string());
        } catch (IOException e) {
            embed.addField("Error", "No pepsi pic this time, sorry.", false);
            e.printStackTrace();
        }
        event.getHook().editOriginalEmbeds(embed.build()).queue();
        embed.clear();
    }
}
