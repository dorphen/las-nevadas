package tk.thebrick.lasnevadas;

import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tk.thebrick.lasnevadas.command.CommandManager;

public class EventListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);
    private static CommandManager commandManager;

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        LOGGER.info("Connected as [{}].", event.getJDA().getSelfUser().getAsTag());

        commandManager = new CommandManager(event.getJDA(), Long.parseLong(Config.get("TEST_GUILD")));  //  FIXME null for pushing commands
    }

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        new Thread(() -> {
            commandManager.runCommand(event);
            System.gc();
        }).start();
    }
}
