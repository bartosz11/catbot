package me.bartosz1.catbot;

import me.bartosz1.catbot.commands.CatCommand;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import net.dv8tion.jda.api.utils.data.DataObject;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class CatBot {

    private final ShardManager bot;
    public static final OkHttpClient http = new OkHttpClient.Builder().callTimeout(3, TimeUnit.SECONDS).connectTimeout(3, TimeUnit.SECONDS).readTimeout(3, TimeUnit.SECONDS).build();
    private static final Logger LOGGER = LoggerFactory.getLogger(CatBot.class);

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
            LOGGER.info("Config file created");
            System.exit(0);
        }
        config = DataObject.fromJson(new FileReader(configFile));
        if (config.isNull("token")) {
            LOGGER.error("Token not found in config file");
            System.exit(1);
        }
        SlashCommandListener cmdMgr = new SlashCommandListener();
        bot = DefaultShardManagerBuilder.createLight(config.getString("token")).setShardsTotal(-1).addEventListeners(cmdMgr, new ReadyListener(this, cmdMgr)).build();
    }

    public ShardManager getBot() {
        return bot;
    }

}
