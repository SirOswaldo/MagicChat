package siroswaldo.magicchat.bukkit;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import siroswaldo.magicchat.util.task.Task;

public class Pruebas extends Task {

    private final MagicChat magicChat;

    public Pruebas(MagicChat magicChat) {
        super(magicChat, 600L);
        this.magicChat = magicChat;
    }

    @Override
    public void actions() {
        magicChat.getLogger().info("================= Task Pruebas");
        magicChat.getLogger().info("Enviando un mensaje de complemento");
        ByteArrayDataOutput output = ByteStreams.newDataOutput();
        output.writeUTF("Mensaje");
        output.writeUTF("Hola amigos esto es un mensaje de pruebas!");
        magicChat.getServer().sendPluginMessage(magicChat, "magic:chat", output.toByteArray());
        magicChat.getLogger().info("Mensaje enviado");
    }
}
