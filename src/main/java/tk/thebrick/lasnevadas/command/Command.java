package tk.thebrick.lasnevadas.command;

import net.dv8tion.jda.api.interactions.commands.SlashCommandInteraction;

import java.util.ArrayList;

public abstract class Command {

    public abstract String getName();
    public abstract String getDescription();

    @SuppressWarnings("SameReturnValue")
    public ArrayList<CommandArg> getCommandArgs(){
        return null;
    }

    public void execute(SlashCommandInteraction interaction){
        interaction.reply("Command not found!").setEphemeral(true).queue();
    }

}
