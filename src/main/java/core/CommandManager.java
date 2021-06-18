package core;

import command.CommandContext;
import command.ICommand;
import command.commands.UtilityCommands.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import util.Config;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class CommandManager {
    public static List<ICommand> commands = new ArrayList<>();

    public CommandManager(){

        addCommand(new pingCommand());
        addCommand(new helpCommand(this));
        addCommand(new minigames.g2048());
        addCommand(new voiceMoveCommand());
        addCommand(new changeNicknameCommand());

        addCommand(new addRoleCommand());
        addCommand(new removeRoleCommand());

    }

    private void addCommand(ICommand cmd){
        boolean nameFound = this.commands.stream().anyMatch((it) -> it.getName().equalsIgnoreCase(cmd.getName()));

        if (nameFound){
            throw new IllegalArgumentException("A command with this name is already pressent");
        }

        commands.add(cmd);
    }
    public List<ICommand> getCommands(String category) {

        List<ICommand> commands = new ArrayList<ICommand>();

        for (ICommand cmd : this.commands) {
            if (cmd.getCategory().equalsIgnoreCase(category)) {
                commands.add(cmd);
            }
        }
        return commands;
    }
    public List<ICommand> getCommands() {
        return commands;
    }

    @Nullable
    public ICommand getCommand(String search){
        String searchLower = search;

        for (ICommand cmd : this.commands) {
            if (cmd.getName().equalsIgnoreCase(searchLower) || cmd.getAliases().contains(searchLower)) {
                return cmd;
            }
        }

        return null;
    }

    public void handle(GuildMessageReceivedEvent event) {
        String[] split = event.getMessage().getContentRaw()
                .replaceFirst("(?i)" + Pattern.quote(Config.PREFIX), "")
                .split("\\s+");

        String invoke = split[0].toLowerCase();
        ICommand cmd = this.getCommand(invoke);

        if (cmd != null) {
            List<String> args = Arrays.asList(split).subList(1, split.length);

            CommandContext ctx = new CommandContext(event, args);

            cmd.handle(ctx);
        }

    }

}