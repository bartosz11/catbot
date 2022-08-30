package me.bartosz1.catbot;

import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.ShardManager;

public class PresenceUpdater implements Runnable {

    private final ShardManager shardManager;

    public PresenceUpdater(ShardManager shardManager) {
        this.shardManager = shardManager;
    }

    @Override
    public void run() {
        shardManager.getShards().forEach(shard -> shard.getPresence().setPresence(Activity.listening(String.format("commands | %d servers | /help", shard.getGuilds().size())), false));
    }
}
