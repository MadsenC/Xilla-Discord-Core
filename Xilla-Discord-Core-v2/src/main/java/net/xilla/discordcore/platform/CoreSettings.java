package net.xilla.discordcore.platform;

import net.xilla.discordcore.DiscordCore;
import net.xilla.discordcore.settings.Settings;

public class CoreSettings extends Settings {

    public CoreSettings() {
        super(DiscordCore.getInstance().getTobiasAPI().getConfigManager().getConfig("settings.json"));
        getInstaller().install("The discord bot's token from https://discord.com/developers/", "token", "blank");
        getInstaller().install("The discord bot's nickname / public name", "bot-name", "blank");
        getInstaller().install("The discord bot's embed coloring (#000000)", "embed-color", "blank");
        getInstaller().install("The prefix for all commands (-)", "command-prefix", "blank");
        getInstaller().install("The bot's current activity or game being played", "activity", "blank");
        if(getConfig().loadDefault("shards", 1))
            getConfig().save();

    }

    public String getBotToken() {
        return getConfig().getString("token");
    }

    public String getBotName() {
        return getConfig().getString("bot-name");
    }

    public String getEmbedColor() {
        return getConfig().getString("embed-color");
    }

    public String getCommandPrefix() {
        return getConfig().getString("command-prefix");
    }

    public String getActivity() {
        return getConfig().getString("activity");
    }
    
    public int getShards() {
        try {
            return getConfig().getInt("shards");
        } catch (ClassCastException ex) {

            return (int)getConfig().getLong("shards");
        }
    }

    public void setBotToken(String key) {
        getConfig().set("token", key);
        getConfig().save();
    }

    public void setBotName(String key) {
        getConfig().set("bot-name", key);
        getConfig().save();
    }

    public void setEmbedColor(String key) {
        getConfig().set("embed-color", key);
        getConfig().save();
    }

    public void setCommandPrefix(String key) {
        getConfig().set("command-prefix", key);
        getConfig().save();
    }

}
