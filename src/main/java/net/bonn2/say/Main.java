package net.bonn2.say;

import net.bonn2.Bot;
import net.bonn2.modules.Module;
import net.dv8tion.jda.api.interactions.commands.DefaultMemberPermissions;
import net.dv8tion.jda.api.interactions.commands.OptionType;
import net.dv8tion.jda.api.interactions.commands.build.CommandData;
import net.dv8tion.jda.api.interactions.commands.build.Commands;
import net.dv8tion.jda.api.interactions.commands.build.SubcommandData;

public class Main extends Module {

    @Override
    public void registerSettings() {

    }

    @Override
    public void load() {
        Bot.jda.addEventListener(new Command());
    }

    @Override
    public CommandData[] getCommands() {
        return new CommandData[] {
                Commands.slash(
                        "say",
                        "Make the bot say something."
                ).addSubcommands(
                        new SubcommandData(
                                "text",
                                "Make the bot send a regular message."
                        ).addOption(
                                OptionType.STRING,
                                "message",
                                "The message to send.",
                                true
                        ).addOption(
                                OptionType.CHANNEL,
                                "channel",
                                "The channel to send the message in.",
                                false
                        ),
                        new SubcommandData(
                                "embed",
                                "Make the bot send an embed"
                        )
                ).setDefaultPermissions(DefaultMemberPermissions.DISABLED)
        };
    }
}
