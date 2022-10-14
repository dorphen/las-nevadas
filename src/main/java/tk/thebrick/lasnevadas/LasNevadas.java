package tk.thebrick.lasnevadas;

import io.github.cdimascio.dotenv.Dotenv;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class LasNevadas {

    private final Dotenv config;
    private final ShardManager shardManager;

    public LasNevadas() throws LoginException {
        config = Dotenv.configure().load();

        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(config.get("TOKEN"))
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Poker"));
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public Dotenv getConfig() {
        return config;
    }

    public static void main(String[] args) {
        try {
            LasNevadas bot = new LasNevadas();
        } catch (LoginException e) {
            System.out.println("ERROR: provided bot token is invalid");
        }
    }
}