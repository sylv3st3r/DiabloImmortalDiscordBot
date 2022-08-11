package me.umbreon.diabloimmortalbot.gameevents.embeds;

import me.umbreon.diabloimmortalbot.languages.LanguageController;
import me.umbreon.diabloimmortalbot.utils.ClientCache;
import me.umbreon.diabloimmortalbot.utils.ImageAssistant;
import me.umbreon.diabloimmortalbot.utils.TimeAssistant;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

public class AncientArenaEmbed {

    private final ClientCache clientCache;

    public AncientArenaEmbed(ClientCache clientCache) {
        this.clientCache = clientCache;
    }

    public void checkAncientArenaFormatted(TextChannel textChannel, String timezone, String language) {
        String time = TimeAssistant.getTimeWithWeekday(timezone);
        String textChannelID = textChannel.getId();

        if (!clientCache.getListWithAncientArenaEmbedTimes().contains(time) || !clientCache.isAncientArenaEmbedMessageEnabled(textChannelID)) {
            return;
        }

        textChannel.sendMessageEmbeds(buildAncientArenaEmbed(timezone, language)).queue();
    }

    private MessageEmbed buildAncientArenaEmbed(String timezone, String language) {
        EmbedBuilder embedBuilder = new EmbedBuilder();

        long unix = TimeAssistant.getTimeInUnix(timezone) + (3600 * 2);

        String eventTitle = LanguageController.getAncientArenaEmbedMessage(language);
        String worldEventMessage = LanguageController.getWorldEventEmbedMessage(language);
        String spawnAtMessage = LanguageController.getSpawnAtMessage(language);
        String countdownMessage = LanguageController.getCountdownEmbedMessage(language);
        String locationMessage1 = LanguageController.getLocationAncientArenaEmbedMessage1(language);
        String locationMessage2 = LanguageController.getLocationAncientArenaEmbedMessage2(language);

        embedBuilder.setTitle(eventTitle + " | " + worldEventMessage);
        embedBuilder.setImage(ImageAssistant.getDiabloAncientArenaImage());
        embedBuilder.addField(spawnAtMessage, "<t:" + unix + ">", true);
        embedBuilder.addField(countdownMessage, "<t:" + unix + ":R>", true);
        embedBuilder.addField(locationMessage1, locationMessage2, false);
        embedBuilder.setThumbnail(ImageAssistant.getDiabloImmortalLogo());

        return embedBuilder.build();
    }

}