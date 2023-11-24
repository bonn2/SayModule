package net.bonn2.say;

import net.dv8tion.jda.api.entities.channel.middleman.MessageChannel;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.utils.FileUpload;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.net.URL;
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
                channel.sendMessage(
                        event.getOption("message").getAsString().replaceAll("\\\\n", "\n")
                ).queue();
            }
            case "embed" -> {
                event.reply("This is not *yet* implemented").setEphemeral(true).queue();
            }
            case "image" -> {
                try {
                    event.reply("Posting...").setEphemeral(true).queue();
                    URL url = new URL(event.getOption("link").getAsString());
                    event.getChannel().sendFiles(FileUpload.fromData(url.openStream(), "image.png")).queue();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
