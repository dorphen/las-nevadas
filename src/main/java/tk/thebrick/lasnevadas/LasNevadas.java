package tk.thebrick.lasnevadas;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.exceptions.InvalidTokenException;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LasNevadas {

    private static final Logger LOGGER = LoggerFactory.getLogger(LasNevadas.class);
    private final ShardManager shardManager;

    public LasNevadas() throws InvalidTokenException {
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(Config.get("TOKEN"))
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Poker"));
        shardManager = builder.build();

        shardManager.addEventListener(new EventListener());
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        try {
            LasNevadas bot = new LasNevadas();
        } catch (InvalidTokenException e) {
            LOGGER.error("Provided bot token is invalid.");
        }
    }
}