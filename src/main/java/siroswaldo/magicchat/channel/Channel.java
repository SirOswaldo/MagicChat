package siroswaldo.magicchat.channel;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import siroswaldo.magicchat.bukkit.MagicChat;
import siroswaldo.magicchat.util.message.StringMessage;

import java.util.ArrayList;
import java.util.List;

public class Channel {

    private String name;
    private boolean Default;
    private String format;
    private String color;
    private String permission;
    private boolean autoJoin;
    private String command;
    private boolean bungee;

    List<Player> players;

    public Channel(String name, boolean Default, String format, String color, String permission, boolean autoJoin, String command, boolean bungee) {
        this.name = name;
        this.Default = Default;
        this.format = format;
        if (color.equals("black")){
            this.color = "&0";
        } else if (color.equals("dark_blue")){
            this.color = "&1";
        }else if (color.equals("dark_green")){
            this.color = "&2";
        }else if (color.equals("dark_aqua")){
            this.color = "&3";
        }else if (color.equals("dark_red")){
            this.color = "&4";
        }else if (color.equals("dark_purple")){
            this.color = "&5";
        }else if (color.equals("gold")){
            this.color = "&6";
        }else if (color.equals("light_gray")){
            this.color = "&7";
        }else if (color.equals("dark_gray")){
            this.color = "&8";
        }else if (color.equals("blue")){
            this.color = "&9";
        }else if (color.equals("green")){
            this.color = "&a";
        }else if (color.equals("aqua")){
            this.color = "&b";
        }else if (color.equals("red")){
            this.color = "&c";
        }else if (color.equals("pink")){
            this.color = "&d";
        }else if (color.equals("yellow")){
            this.color = "&e";
        }else{
            this.color = "&f";
        }
        this.permission = permission;
        this.autoJoin = autoJoin;
        this.command = command;
        this.bungee = bungee;

        players = new ArrayList<>();
    }


    public void sendMessage(String message){
        for(Player player:players){
            player.sendMessage(message);
        }
    }

    /**
     * Envia el mensaje al canal por medio de un jugador
     * @param @{@link Player} Jugador que envio el comando
     * @param @{@link String} Mensaje que envio el jugador
     */
    public void sendMessage(MagicChat magicChat, Player sender, String text){
        StringMessage message = new StringMessage(format + color);
        message.addPlaceHolders(sender);
        message.addColor();
        message.appendText(text);
        if (bungee){
            ByteArrayDataOutput out = ByteStreams.newDataOutput();
            out.writeUTF("SendMessage");
            out.writeUTF(name);
            out.writeUTF(message.getMessage());
            magicChat.getServer().sendPluginMessage(magicChat, "MagicChat", out.toByteArray());
        }else{
            for(Player player:players){
                message.sendMessage(player);
            }
            message.logConsole(magicChat.getServer());
        }
    }

    /**
     * Devuelve verdadero si es el canal por defecto
     * @return
     */
    public boolean isDefault() {
        return Default;
    }

    /**
     * Establece este canal como canal por defecto
     * @param aDefault
     */
    public void setDefault(boolean aDefault) {
        Default = aDefault;
    }

    /**
     * Devuelve verdadero si el canal tiene al jugador
     * @param player
     * @return boolean
     */
    public boolean containPlayer(Player player){
        return players.contains(player);
    }

    /**
     * Agrega un jugador al canal
     * @param player
     */
    public void addPlayer(Player player){
        players.add(player);
    }

    /**
     * Remueve un jugador al canal
     * @param player
     */
    public void removePlayer(Player player) {
        players.remove(player);
    }

    /**
     * Obtiene el nombre del canal
     * @return String | Nombre del canal
     */
    public String getName() {
        return name;
    }

    /**
     * Obtener el formato del canal
     * @return
     */
    public String getFormat() {
        return format;
    }

    /**
     * Establecer el formato del canal
     * @param format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Obtener el color del texto del canal
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     * Establecer el color del texto del canal
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * Obtener el permiso para usar el canal
     * @return
     */
    public String getPermission() {
        return permission;
    }

    /**
     * Establecer el permiso para usar el canal
     * @param permission
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * Devuelve verdadero si entra automaticamente al canal
     * @return
     */
    public boolean isAutoJoin() {
        return autoJoin;
    }

    /**
     * Establecer si se entrara automaticamente al canal
     * @param autoJoin
     */
    public void setAutoJoin(boolean autoJoin) {
        this.autoJoin = autoJoin;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    /**
     * Devuelve verdadero si el canal esta activado como canal proxy
     * @return
     */
    public boolean isBungee() {
        return bungee;
    }

    /**
     * Establecer si el canal es un canal proxy
     * @param bungee
     */
    public void setBungee(boolean bungee) {
        this.bungee = bungee;
    }
}
