package net.xilla.discordcore;

import com.tobiassteely.tobiasapi.TobiasAPI;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder;
import net.xilla.discordcore.command.CommandEventHandler;
import net.xilla.discordcore.command.CommandManager;
import net.xilla.discordcore.command.type.basic.BasicCommand;
import net.xilla.discordcore.command.type.basic.BasicCommandExecutor;
import net.xilla.discordcore.command.CommandResponse;
import net.xilla.discordcore.command.type.template.type.TextCommand;
import net.xilla.discordcore.platform.Platform;
import net.xilla.discordcore.platform.CoreSettings;
import net.xilla.discordcore.staff.StaffManager;

import javax.security.auth.login.LoginException;

public class DiscordCore {

    private static DiscordCore instance;

    public static DiscordCore getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        new DiscordCore(Platform.getPlatform.STANDALONE.getPlatform());
    }

    private TobiasAPI api;
    private Platform platform;
    private JDA bot;
    private CoreSettings settings;

    public DiscordCore(String platformType) {
        instance = this;
        this.api = new TobiasAPI(); // Loads base APIs

        this.settings = new CoreSettings(); // Loads Core Settings

        this.platform = new Platform(platformType); // Loads the rest of the core
        this.platform.getCommandManager().getCommandWorker().start();

        try {
            JDABuilder shardBuilder = new JDABuilder(settings.getBotToken());
//
//            for (int i = 0; i < settings.getShards(); i++) {
//                shardBuilder.useSharding(i, settings.getShards()).build();
//            }

            shardBuilder.setActivity(Activity.playing(settings.getActivity()));

            this.bot = shardBuilder.build();
        } catch (LoginException ex) {
            ex.printStackTrace();
        }

        this.bot.addEventListener(new CommandEventHandler());

        getCommandManager().getTemplateManager().reload();
    }

    public JDA getBot() {
        return bot;
    }

    public TobiasAPI getTobiasAPI() {
        return api;
    }

    public Platform getPlatform() {
        return platform;
    }

    public CoreSettings getSettings() {
        return settings;
    }

    public CommandManager getCommandManager() {
        return getPlatform().getCommandManager();
    }

    public StaffManager getStaffManager() {
        return getPlatform().getStaffManager();
    }
}
