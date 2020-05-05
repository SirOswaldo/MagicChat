package siroswaldo.magicchat.channel;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.entity.Player;
import siroswaldo.magicchat.MagicChat;
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
    private List<String> aliases;
    private boolean bungee;

    List<Player> players;

    public Channel(String name, boolean Default, String format, String color, String permission, boolean autoJoin, String command, List<String> aliases, boolean bungee) {
        this.name = name;
        this.Default = Default;
        this.format = format;
        this.color = color;
        this.permission = permission;
        this.autoJoin = autoJoin;
        this.command = command;
        this.aliases = aliases;
        this.bungee = bungee;

        players = new ArrayList<>();
    }

    /**
     * Envia el mensaje al canal por medio de un jugador
     * @param @{@link Player} Jugador que envio el comando
     * @param @{@link String} Mensaje que envio el jugador
     */
    public void sendMessage(Player sender, String text){
        StringMessage message = new StringMessage(format + color);
        message.addPlaceHolders(sender);
        message.addColor();
        for(Player player:players){
            player.sendMessage(message.toString() + text);
        }
    }

    public void sendMessageBungee(MagicChat magicChat, Player player, String text){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF("network");
        StringMessage message = new StringMessage(format + color);
        message.addPlaceHolders(player);
        message.addColor();
        out.writeUTF(message.toString());
        player.sendPluginMessage(magicChat, "MagicChat", out.toByteArray());
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
     * Obtener los alias del canal
     * @return
     */
    public List<String> getAliases() {
        return aliases;
    }

    /**
     * Establecer los alias del canal
     * @param aliases
     */
    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
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
