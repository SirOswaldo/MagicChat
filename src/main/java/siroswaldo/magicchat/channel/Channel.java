package siroswaldo.magicchat.channel;

import java.util.List;

public class Channel {

    private String name;
    private String format;
    private String color;
    private String permission;
    private boolean autoJoin;
    private List<String> aliases;

    public Channel(String name,String format, String color, String permission, boolean autoJoin, List<String> aliases) {
        this.name = name;
        this.format = format;
        this.color = color;
        this.permission = permission;
        this.autoJoin = autoJoin;
        this.aliases = aliases;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public boolean isAutoJoin() {
        return autoJoin;
    }

    public void setAutoJoin(boolean autoJoin) {
        this.autoJoin = autoJoin;
    }

    public List<String> getAliases() {
        return aliases;
    }

    public void setAliases(List<String> aliases) {
        this.aliases = aliases;
    }
}
