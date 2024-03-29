package command;

import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;

import java.io.IOException;
import java.util.List;

public interface ICommand {
    void handle(SlashCommandInteractionEvent event) throws IOException;

    String getName();

    String getHelp();

    CommandData getCommandData();

    CommandData commandDaten = null;

    default String getButtonPrefix(){return null;};

    default void ButtonAction(ButtonInteractionEvent event) {}
}