package com.narxoz.rpg.council;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.GuildMediator;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

/**
 * Orchestrates a planning session that uses both Iterator and Mediator.
 */
public class CouncilEngine {

    public CouncilRunResult runCouncil(List<Hero> party, QuestLog questLog, GuildMediator hall) {
        int questsTraversed = 0;

        System.out.println("\n--- Council Phase 1: Review all quests (arrival order) ---");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            Quest quest = ordered.next();
            questsTraversed++;
            System.out.println("Reviewing: " + quest);
            hall.dispatch("orders", null, "Plan quest: " + quest.getTitle());
        }

        System.out.println("\n--- Council Phase 2: Urgent quests (priority >= HIGH) ---");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            Quest quest = priority.next();
            questsTraversed++;
            System.out.println("PRIORITY quest: " + quest);
            hall.dispatch("urgent", null, "Urgent attention needed: " + quest.getTitle());
            hall.dispatch("supplies", null, "Prepare supplies for: " + quest.getTitle());
            hall.dispatch("healing", null, "Ready medical support for: " + quest.getTitle());
            hall.dispatch("scouting", null, "Scout area for: " + quest.getTitle());
        }

        int messagesRouted = 0;
        int membersNotified = 0;
        if (hall instanceof GuildHall) {
            GuildHall guildHall = (GuildHall) hall;
            messagesRouted = guildHall.getTotalMessagesRouted();
            membersNotified = guildHall.getTotalMembersNotified();
        }

        return new CouncilRunResult(questsTraversed, messagesRouted, membersNotified);
    }
}
