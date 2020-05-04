package siroswaldo.magicchat.commands;

import com.sun.org.apache.xerces.internal.xs.StringList;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import siroswaldo.magicchat.MagicChat;
import siroswaldo.magicchat.util.message.ListMessage;
import siroswaldo.magicchat.util.message.StringMessage;

public class MagicChatCommand implements CommandExecutor {

    private final MagicChat magicChat;

    public MagicChatCommand(MagicChat magicChat) {
        this.magicChat = magicChat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        // magicchat help
        // magicchat reload
        // magicchat version

        FileConfiguration messages = magicChat.getYamlFiles().getFileConfiguration("messages");
        String prefix = messages.getString("prefix");
        if (sender.hasPermission("magicchat.admin")){
            if (args.length >= 1){
                if (args[0].equalsIgnoreCase("help")){
                    ListMessage help = new ListMessage(messages.getStringList("magicChat.help"));
                    help.addColor();
                    help.sendMessage(sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("reload")){
                    magicChat.getYamlFiles().reloadFiles();
                    magicChat.getChannelsMap().reloadChannels();
                    magicChat.getPlayerDataMap().reloadPlayerData();
                    StringMessage reloadComplete = new StringMessage(prefix + messages.getString("magicChat.reloadComplete"));
                    reloadComplete.addColor();
                    reloadComplete.sendMessage(sender);
                    return true;
                } else if (args[0].equalsIgnoreCase("version")){
                    ListMessage version = new ListMessage();
                    version.addLine("&3&l>>>>>>>> &b&lMagic&9&lChat&b&lVersion &3&l<<<<<<<<");
                    version.addLine(" &6&lAuthor &e&l>>> &bSirOswaldo");
                    version.addLine(" &6&lWeb &e&l>>> &bhttps://kayland.tk");
                    version.addLine(" &6&lVersion &e&l>>> &b1.0.0");
                    version.addLine("&3&l>>>>>>>> &b&lMagic&9&lChat&b&lVersion &3&l<<<<<<<<");
                    version.addColor();
                    version.sendMessage(sender);
                    return true;
                } else{
                    StringMessage invalidArg = new StringMessage(prefix + messages.getString("magicChat.invalidArg"));
                    invalidArg.addColor();
                    invalidArg.sendMessage(sender);
                    return true;
                }
            } else {
                StringMessage emptyArg = new StringMessage(prefix + messages.getString("magicChat.emptyArg"));
                emptyArg.addColor();
                emptyArg.sendMessage(sender);
                return true;
            }
        } else {
            StringMessage noPermission = new StringMessage(prefix + messages.getString("magicChat.noPermission"));
            noPermission.addColor();
            noPermission.sendMessage(sender);
            return true;
        }
    }

}
