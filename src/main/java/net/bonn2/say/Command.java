package net.bonn2.say;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;
import java.util.Objects;

public class Command extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@Nonnull SlashCommandInteractionEvent event) {
        if (!event.getName().equals("say")) return;
        switch (Objects.requireNonNull(event.getSubcommandName())) {
            case "text" -> {
                TextChannel channel = event.getTextChannel();
                if (event.getOption("channel") != null)
                    channel = Objects.requireNonNull(event.getOption("channel")).getAsTextChannel();
                if (channel != null) {
                    event.reply("Sending message in %s".formatted(channel.getAsMention())).setEphemeral(true).queue();
                    channel.sendMessage(Objects.requireNonNull(event.getOption("message")).getAsString()).queue();
                } else {
                    event.reply("That is not a valid text channel!").setEphemeral(true).queue();
                }
            }
            case "embed" -> {
                event.reply("This is not *yet* implemented").setEphemeral(true).queue();
            }
        }
    }
}
