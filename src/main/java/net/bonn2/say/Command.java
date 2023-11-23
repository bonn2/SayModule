package net.bonn2.say;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class Command extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("say")) return;
        switch (Objects.requireNonNull(event.getSubcommandName())) {
            case "text" -> {
                MessageChannel channel = event.getChannel();
                if (event.getOption("channel") != null)
                    channel = (MessageChannel) Objects.requireNonNull(event.getOption("channel")).getAsChannel();
                event.reply("Sending message in %s".formatted(channel.getAsMention())).setEphemeral(true).queue();
                channel.sendMessage(Objects.requireNonNull(event.getOption("message")).getAsString()).queue();
            }
            case "embed" -> {
                event.reply("This is not *yet* implemented").setEphemeral(true).queue();
            }
        }
    }
}
