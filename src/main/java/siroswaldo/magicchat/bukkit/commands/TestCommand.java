package siroswaldo.magicchat.bukkit.commands;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import siroswaldo.magicchat.bukkit.MagicChat;

public class TestCommand implements CommandExecutor {


    private final MagicChat magicChat;

    public TestCommand (MagicChat magicChat){
        this.magicChat = magicChat;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("message:test");
        out.writeUTF("Este es un mensaje de pruebas!");
        magicChat.getServer().sendPluginMessage(magicChat,"magic:chat", out.toByteArray());
        return false;
    }
}
