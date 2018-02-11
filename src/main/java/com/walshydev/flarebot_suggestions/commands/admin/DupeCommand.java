package com.walshydev.flarebot_suggestions.commands.admin;

import com.walshydev.flarebot_suggestions.FlareBotSuggestions;
import com.walshydev.flarebot_suggestions.Suggestion;
import com.walshydev.flarebot_suggestions.SuggestionsManager;
import com.walshydev.jba.commands.Command;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.Message;
import net.dv8tion.jda.core.entities.MessageChannel;
import net.dv8tion.jda.core.entities.User;

public class DupeCommand implements Command {

    @Override
    public void onCommand(User user, MessageChannel channel, Message message, String[] args, Member member) {
        if (FlareBotSuggestions.getInstance().isStaff(user)) {
            if (args.length == 2) {
                int id;
                int dupeId;
                try {
                    id = Integer.parseInt(args[0]);
                    dupeId = Integer.parseInt(args[1]);
                } catch (NumberFormatException e) {
                    channel.sendMessage("Invalid ID!").queue();
                    return;
                }

                SuggestionsManager.getInstance().mergeSuggestions(id, dupeId);
                channel.sendMessage("Merged #" + dupeId + " into " + id).queue();
            } else
                channel.sendMessage("**Usage**: `dupe <id> <dupe id>`").queue();
        }
    }

    @Override
    public String getCommand() {
        return "dupe";
    }

    @Override
    public String getDescription() {
        return "Merge dupe suggestions";
    }
}
