package me.bartosz1.catbot;

import me.bartosz1.catbot.commands.CatCommand;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import okhttp3.OkHttpClient;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class CatBot {

    //
    //TODO LOGBACK EVERYWHERE
    //

    private final ShardManager bot;
    public static final OkHttpClient http = new OkHttpClient.Builder().callTimeout(3000, TimeUnit.SECONDS).connectTimeout(3000, TimeUnit.SECONDS).readTimeout(3000, TimeUnit.SECONDS).build();
    private final SlashCommandListener cmdMgr = new SlashCommandListener();
    public static void main(String[] args) throws Exception {
        new CatBot();
    }

    public CatBot() throws IOException, LoginException {
        DataObject config;
        File configFile = new File("config.json");
        if (!configFile.exists()) {
            configFile.createNewFile();
            PrintWriter writer = new PrintWriter(configFile);
            writer.println("{\"token\":\"REPLACE_THIS_WITH_YOUR_TOKEN\"}");
            writer.close();
            System.out.println("Config file created. Please fill it with required data.");
            System.exit(0);
        }
        config = DataObject.fromJson(new FileReader(configFile));
        if (config.isNull("token")) {
            System.out.println("ERROR: Token not found in config file.");
            System.exit(1);
        }
        bot = DefaultShardManagerBuilder.createLight(config.getString("token")).setShardsTotal(-1).addEventListeners(cmdMgr, new ReadyListener(this, cmdMgr)).build();
    }

    public ShardManager getBot() {
        return bot;
    }

}
