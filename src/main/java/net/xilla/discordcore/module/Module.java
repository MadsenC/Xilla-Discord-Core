package net.xilla.discordcore.module;

import com.tobiassteely.tobiasapi.TobiasAPI;
import com.tobiassteely.tobiasapi.api.manager.ManagerObject;
import com.tobiassteely.tobiasapi.command.CommandManager;
import com.tobiassteely.tobiasapi.config.ConfigManager;
import net.xilla.discordcore.DiscordCore;
import net.xilla.discordcore.core.Platform;
import net.xilla.discordcore.core.staff.GroupManager;

public class Module extends ManagerObject {

    private String type;
    private String name;
    private String version;

    public Module(String type, String name, String version) {
        super(name);
        this.type = type;
        this.name = name;
        this.version = version;
    }

    public void onEnable() {

    }

    public void onDisable() {

    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getVersion() {
        return version;
    }

    public DiscordCore getDiscordCore() {
        return DiscordCore.getInstance();
    }

    public Platform getPlatform() {
        return DiscordCore.getInstance().getPlatform();
    }

    public TobiasAPI getTobiasAPI() {
        return DiscordCore.getInstance().getTobiasAPI();
    }

    public CommandManager getCommandManager() {
        return DiscordCore.getInstance().getPlatform().getCommandManager();
    }

    public GroupManager getGroupManager() {
        return DiscordCore.getInstance().getPlatform().getGroupManager();
    }

    public ConfigManager getConfigManager() {
        return DiscordCore.getInstance().getTobiasAPI().getConfigManager();
    }

}
