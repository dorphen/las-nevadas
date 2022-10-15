package tk.thebrick.lasnevadas;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class LasNevadas {

    private final ShardManager shardManager;

    public LasNevadas() throws LoginException {
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
        } catch (LoginException e) {
            System.out.println("ERROR: provided bot token is invalid");
        }
    }
}