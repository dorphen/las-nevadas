package tk.thebrick.lasnevadas.command.basic;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;
import tk.thebrick.lasnevadas.command.Command;

public class Ping extends Command {

    @Override
    public String getName() {
        return "ping";
    }

    @Override
    public String getDescription() {
        return "Check bot status and latency.";
    }

    @Override
    public void execute(SlashCommandInteraction interaction) {
        long time = System.currentTimeMillis();
        interaction.reply("Pong!").setEphemeral(true)
                .flatMap(v ->
                        interaction.getHook().editOriginalFormat("Pong!\nLatency: %d ms",  System.currentTimeMillis() - time)
                ).queue();
    }
}
