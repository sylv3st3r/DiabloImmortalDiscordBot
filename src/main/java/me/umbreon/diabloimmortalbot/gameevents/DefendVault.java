package me.umbreon.diabloimmortalbot.gameevents;

import me.umbreon.diabloimmortalbot.languages.LanguageController;
import me.umbreon.diabloimmortalbot.utils.ClientCache;
import me.umbreon.diabloimmortalbot.utils.TimeAssistant;

public class DefendVault {

    private final ClientCache clientCache;

    public DefendVault(ClientCache clientCache) {
        this.clientCache = clientCache;
    }

    public String checkDefendVault(String timezone, String language, String guildID, String textChannelID) {

        if (!isTimeValid(timezone)) return "";
        if (!clientCache.isDefendVaultMessageEnabled(textChannelID)) return "";

        if (isHeadUpTime(timezone)) {
            if (!clientCache.isHeadUpOnServerEnabled(guildID) || !clientCache.isHeadUpMessageOnChannelEnabled(textChannelID)) {
                return "";
            }

            return LanguageController.getDefendTheVaultHeadUpMessage(language) + "\n";

        } else {
            if (!clientCache.isEventMessageOnServerEnabled(guildID) || !clientCache.isEventMessageOnChannelEnabled(textChannelID)) {
                return "";
            }

            return LanguageController.getDefendTheVaultMessage(language) + "\n";
        }
    }

    private boolean isTimeValid(String timezone) {
        String time = TimeAssistant.getTime(timezone);
        return clientCache.getListWithVaultTimes().get(time) != null;
    }

    private boolean isHeadUpTime(String timezone) {
        String time = TimeAssistant.getTime(timezone);
        return clientCache.getListWithVaultTimes().get(time);
    }

}
