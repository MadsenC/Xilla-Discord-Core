package net.xilla.discordcore.command.response;

import com.tobiassteely.tobiasapi.command.CommandData;
import com.tobiassteely.tobiasapi.command.response.CommandResponder;
import com.tobiassteely.tobiasapi.command.response.CommandResponse;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.xilla.discordcore.CoreObject;
import net.xilla.discordcore.command.CoreCommandExecutor;
import net.xilla.discordcore.command.event.BungeeCommandEvent;
import net.xilla.discordcore.command.event.SpigotCommandEvent;
import org.bukkit.ChatColor;


public class CoreCommandResponder extends CoreObject implements CommandResponder {

    // Overriding the internal send command with title
    @Override
    public void send(CommandResponse response) {
        // Sends a discord response if it's a discord command
        if(response.getInputType().equals(CoreCommandExecutor.discord_input)) {

            MessageReceivedEvent event = (MessageReceivedEvent)response.getData().get(); // Pulls the event from the command data

            if(response instanceof CoreCommandResponse) {
                CoreCommandResponse coreResponse = (CoreCommandResponse)response;
                if(coreResponse.getEmbed() != null) {
                    event.getTextChannel().sendMessage(coreResponse.getEmbed()).queue();
                    return;
                }
            }

            if(response.getTitle() != null)
                event.getTextChannel().sendMessage(response.getTitle() + "\n" + response.getDescription()).queue(); // Sends the discord command response
            else
                event.getTextChannel().sendMessage(response.getDescription()).queue(); // Sends the discord command response

        // Sends a response back to spigot
        } else if(response.getInputType().equals(CoreCommandExecutor.bungee_input)) {
            BungeeCommandEvent event = (BungeeCommandEvent)response.getData().get(); // Pulls the event from the command data

            if(response.getTitle() != null)
                event.getSender().sendMessage(new ComponentBuilder(response.getTitle()).color(net.md_5.bungee.api.ChatColor.RED).create());

            event.getSender().sendMessage(new ComponentBuilder(response.getDescription()).color(net.md_5.bungee.api.ChatColor.RED).create());

        // Sends a response back to bungee
        } else if(response.getInputType().equals(CoreCommandExecutor.spigot_input)) {
            SpigotCommandEvent event = (SpigotCommandEvent)response.getData().get(); // Pulls the event from the command data

            if(response.getTitle() != null)
                event.getSender().sendMessage(ChatColor.RED.toString() + ChatColor.BOLD.toString() + response.getTitle());

            event.getSender().sendMessage(ChatColor.RED.toString() + response.getDescription());

        // Sends a response back to command line
        } else {
            if(response.getTitle() != null)
               getLog().sendMessage(0, response.getTitle(), response.getDescription());
            else
                getLog().sendMessage(0, response.getDescription());
        }
    }

    // Implementing a new Embed supported option for discord core commands.
    public void send(MessageEmbed embed, String inputType, CommandData data) {

        // Sends a discord response if it's a discord command
        if(inputType.equals(CoreCommandExecutor.discord_input)) {
            MessageReceivedEvent event = (MessageReceivedEvent)data.get(); // Pulls the event from the command data
            event.getTextChannel().sendMessage(embed).queue();

        // Sends a response back to spigot
        } else if(inputType.equals(CoreCommandExecutor.bungee_input)) {
            BungeeCommandEvent event = (BungeeCommandEvent)data.get(); // Pulls the event from the command data
            if(embed.getTitle() != null) {
                event.getSender().sendMessage(new ComponentBuilder(embed.getTitle()).color(net.md_5.bungee.api.ChatColor.RED).create());
            }
            event.getSender().sendMessage(new ComponentBuilder(embed.getDescription()).color(net.md_5.bungee.api.ChatColor.RED).create());

        // Sends a response back to bungee
        } else if(inputType.equals(CoreCommandExecutor.spigot_input)) {
            SpigotCommandEvent event = (SpigotCommandEvent)data.get(); // Pulls the event from the command data
            if(embed.getTitle() != null) {
                event.getSender().sendMessage(ChatColor.RED.toString() + ChatColor.BOLD.toString() + embed.getTitle());
            }
            event.getSender().sendMessage(ChatColor.RED.toString() + embed.getDescription());

        // Sends a response back to command line
        } else {
            if(embed.getTitle() != null) {
                getLog().sendMessage(0, embed.getTitle(), embed.getDescription());
            }
            getLog().sendMessage(0, embed.getDescription());
        }
    }

}
