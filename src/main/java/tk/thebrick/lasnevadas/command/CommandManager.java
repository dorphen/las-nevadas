package tk.thebrick.lasnevadas.command;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SlashCommandData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Objects;

public class CommandManager {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommandManager.class);
    private final ArrayList<Command> commands;

    public ArrayList<Command> getCommands() {
        return commands;
    }

    public CommandManager(JDA jda, Long guildID) {
        commands = new ArrayList<>();

        // commands.add(new CommandName());

        registerCommands(jda, guildID);
    }

    private void registerCommands(JDA jda, Long guildID) {

        if (guildID != null) {
            try {
                for (Command command : getCommands()) {
                    if (command.getCommandArgs() == null) {  // no options
                        Objects.requireNonNull(jda.getGuildById(guildID)).upsertCommand(command.getName(), command.getDescription()).queue();
                    } else {
                        SlashCommandData commandData = Commands.slash(command.getName(), command.getDescription());
                        for (CommandArg arg : command.getCommandArgs()) {
                            commandData = commandData.addOption(arg.optionType(), arg.name(), arg.description(), arg.required());
                        }
                        Objects.requireNonNull(jda.getGuildById(guildID)).upsertCommand(commandData).queue();
                    }
                }
            } catch (NullPointerException e) {
                LOGGER.error("Invalid Guild ID: {}.", guildID);
            }
        } else {
            for (Command command : getCommands()) {
                if (command.getCommandArgs() == null) {  // no options
                    jda.upsertCommand(command.getName(), command.getDescription()).queue();
                } else {
                    SlashCommandData commandData = Commands.slash(command.getName(), command.getDescription());
                    for (CommandArg arg : command.getCommandArgs()) {
                        commandData = commandData.addOption(arg.optionType(), arg.name(), arg.description(), arg.required());
                    }
                    jda.upsertCommand(commandData).queue();
                }
            }
        }

    }

}
