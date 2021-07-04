package core;

import command.ICommand;
import net.dv8tion.jda.api.entities.Emoji;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.interaction.SlashCommandEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.commands.Command;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.OptionData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandGroupData;
import net.dv8tion.jda.api.interactions.commands.privileges.CommandPrivilege;
import util.Config;

import java.util.List;

import static core.DiscordBot.jda;

public class ReadyListener extends ListenerAdapter {
    @Override
    public void onReady(ReadyEvent event){

        Guild guild = event.getJDA().getGuildById(790885170411601920l);

        for (ICommand cmd:CommandManager.commands) {

            guild.upsertCommand(cmd.getCommandData()).queue(command -> {

                command.updatePrivileges(guild, List.of(
                        CommandPrivilege.enable(jda.getUserById(Config.ownerId)),
                        CommandPrivilege.enable(guild.getRoleById(791265130489839628L))
                        )
                ).queue();

            });
        }

    }




}
