package me.umbreon.diabloimmortalbot.gameevents;

import me.umbreon.diabloimmortalbot.configuration.LanguageController;
import me.umbreon.diabloimmortalbot.database.DatabaseRequests;
import me.umbreon.diabloimmortalbot.utils.ClientCache;
import me.umbreon.diabloimmortalbot.utils.Time;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageEmbed;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

public class AncientNightMare {

    private final Map<String, Boolean> listAncientNightmare;
    private final ClientCache clientCache;

    public AncientNightMare(DatabaseRequests databaseRequests, ClientCache clientCache) {
        listAncientNightmare = databaseRequests.getEventTimes("event_ancient_nightmare", false);
        this.clientCache = clientCache;
    }

    public String checkAncientNightMare(String timezone, String language, String guildID) {
        if (!isTimeValid(timezone)) return "";

        if (isHeadUpTime(timezone) && clientCache.getHeadUpValue(guildID)) {
            return LanguageController.getAncientNightmareHeadUpMessage(language) + "\n";
        } else {
            return LanguageController.getAncientNightmareMessage(language) + "\n";
        }
    }

    private boolean isTimeValid(String timezone) {
        String time = Time.getTimeWithWeekday(timezone);
        return listAncientNightmare.get(time) != null;
    }

    private boolean isHeadUpTime(String timezone) {
        String time = Time.getTimeWithWeekday(timezone);
        return listAncientNightmare.get(time);
    }

}
