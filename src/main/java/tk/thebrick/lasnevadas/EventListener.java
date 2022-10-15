package tk.thebrick.lasnevadas;

import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EventListener extends ListenerAdapter {

    private static final Logger LOGGER = LoggerFactory.getLogger(EventListener.class);

    @Override
    public void onReady(@NotNull ReadyEvent event) {
        LOGGER.info("Connected as {}.", event.getJDA().getSelfUser().getAsTag());
    }
}
