package tk.thebrick.lasnevadas.command;

import net.dv8tion.jda.api.interactions.commands.OptionType;

public record CommandArg(OptionType optionType, String name, String description, Boolean required) { }
