package util;


import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.ClientType;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.VoiceChannel;
import util.Config;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static core.Main.shardMan;

public class Infos {
    public static void main() {
        TimeandDate();
        Useranzahl();
        Online();
    }
    public static void TimeandDate() {


        SimpleDateFormat timeformat = new SimpleDateFormat("kk:mm");
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Guild guild = shardMan.getGuildById(Config.GUILDID);
        VoiceChannel channel = guild.getVoiceChannelById("704268736374243459");
        channel.getManager().setName("⌚"+ timeformat.format(new Date()) +"📅"+ dateFormat.format(new Date())).complete();

    }
    public static void Useranzahl(){
        Guild guild = shardMan.getGuildById(Config.GUILDID);
        VoiceChannel channel = guild.getVoiceChannelById("704268797795762187");
        channel.getManager().setName("\uD83D\uDCC8Useranzahl: "+guild.getMemberCount()).complete();
    }
    public static void Online(){
        int onlineMembers = 0;
        Guild guild = shardMan.getGuildById(Config.GUILDID);
        VoiceChannel channel = guild.getVoiceChannelById("704268857736691793");
        List<Member> members = guild.getMembers();
        for (Member m: members) {
            if (m.getOnlineStatus() != OnlineStatus.OFFLINE){
                onlineMembers++;
            }
        }
        channel.getManager().setName("\uD83D\uDD35Online: "+ (onlineMembers-6)).complete();
    }
}