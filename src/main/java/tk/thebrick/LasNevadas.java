package tk.thebrick;

import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.dv8tion.jda.api.sharding.ShardManager;

import javax.security.auth.login.LoginException;

public class Main {

    private final ShardManager shardManager;

    public Main() throws LoginException {
        String token = "MTAzMDMxODgzNjY1MTY1NTE5OA.G5lAYd.TneMjqimPQDtW5bZm5zlp92SYsRaEeUYRP1ePE";
        DefaultShardManagerBuilder builder = DefaultShardManagerBuilder.createDefault(token)
                .setStatus(OnlineStatus.ONLINE)
                .setActivity(Activity.playing("Poker"));
        shardManager = builder.build();
    }

    public ShardManager getShardManager() {
        return shardManager;
    }

    public static void main(String[] args) {
        Main bot =  
    }
}