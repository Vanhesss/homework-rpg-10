package com.narxoz.rpg;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.council.CouncilEngine;
import com.narxoz.rpg.council.CouncilRunResult;
import com.narxoz.rpg.guild.Captain;
import com.narxoz.rpg.guild.GuildHall;
import com.narxoz.rpg.guild.Healer;
import com.narxoz.rpg.guild.Quartermaster;
import com.narxoz.rpg.guild.Scout;
import com.narxoz.rpg.quest.Quest;
import com.narxoz.rpg.quest.QuestIterator;
import com.narxoz.rpg.quest.QuestLog;
import com.narxoz.rpg.quest.QuestPriority;
import java.util.List;

/**
 * Entry point for Homework 10 — The Adventurers' Guild: Iterator + Mediator.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== Homework 10 Demo: Iterator + Mediator ===\n");

        // 1. Create heroes
        Hero warrior = new Hero("Aldric the Bold", 120, 0, 25, 18, 50);
        Hero mage = new Hero("Elara the Wise", 70, 100, 35, 8, 30);
        List<Hero> party = List.of(warrior, mage);

        System.out.println("--- Party Members ---");
        for (Hero hero : party) {
            System.out.println(hero);
        }

        // 2. Build QuestLog with 5+ quests
        QuestLog questLog = new QuestLog();
        questLog.add(new Quest("Hunt the Forest Wolves", QuestPriority.LOW, 50, false));
        questLog.add(new Quest("Escort the Merchant Caravan", QuestPriority.NORMAL, 120, false));
        questLog.add(new Quest("Clear the Cursed Ruins", QuestPriority.HIGH, 300, false));
        questLog.add(new Quest("Slay the Dragon of Mount Ashen", QuestPriority.URGENT, 1000, true));
        questLog.add(new Quest("Retrieve the Lost Amulet", QuestPriority.NORMAL, 200, false));
        questLog.add(new Quest("Defend the Village from Undead", QuestPriority.HIGH, 400, true));

        // 3. Show Iterator pattern — ordered traversal
        System.out.println("\n========================================");
        System.out.println("  ITERATOR PATTERN DEMONSTRATION");
        System.out.println("========================================");

        System.out.println("\n--- Quests in arrival order (OrderedQuestIterator) ---");
        QuestIterator ordered = questLog.ordered();
        while (ordered.hasNext()) {
            System.out.println("  " + ordered.next());
        }

        System.out.println("\n--- Quests in reverse order (ReverseQuestIterator) ---");
        QuestIterator reverse = questLog.reverse();
        while (reverse.hasNext()) {
            System.out.println("  " + reverse.next());
        }

        System.out.println("\n--- High+ priority quests (PriorityQuestIterator, threshold=HIGH) ---");
        QuestIterator priority = questLog.priorityAtLeast(QuestPriority.HIGH);
        while (priority.hasNext()) {
            System.out.println("  " + priority.next());
        }

        // 4. Show Mediator pattern
        System.out.println("\n========================================");
        System.out.println("  MEDIATOR PATTERN DEMONSTRATION");
        System.out.println("========================================");

        GuildHall hall = new GuildHall();
        Quartermaster quartermaster = new Quartermaster("Brom", hall);
        Scout scout = new Scout("Lyra", hall);
        Healer healer = new Healer("Mira", hall);
        Captain captain = new Captain("General Thane", hall);

        System.out.println("\n--- Captain issues an order ---");
        captain.issueOrder("orders", "All units prepare for the dragon hunt!");

        System.out.println("\n--- Scout reports route ---");
        scout.reportRoute("scouting", "Mountain pass is clear but watch for wyverns.");

        System.out.println("\n--- Quartermaster requests supplies ---");
        quartermaster.requestSupplies("supplies", "Need 20 fire-resistant potions.");

        System.out.println("\n--- Healer prepares aid ---");
        healer.prepareAid("healing", "Anti-venom and burn salves are ready.");

        System.out.println("\n--- Urgent dispatch ---");
        captain.issueOrder("urgent", "Dragon spotted near the eastern ridge!");

        // 5. Run CouncilEngine
        System.out.println("\n========================================");
        System.out.println("  COUNCIL ENGINE RUN");
        System.out.println("========================================");

        GuildHall councilHall = new GuildHall();
        new Quartermaster("Brom", councilHall);
        new Scout("Lyra", councilHall);
        new Healer("Mira", councilHall);
        new Captain("General Thane", councilHall);

        CouncilEngine engine = new CouncilEngine();
        CouncilRunResult result = engine.runCouncil(party, questLog, councilHall);

        System.out.println("\n========================================");
        System.out.println("  COUNCIL RUN RESULT");
        System.out.println("========================================");
        System.out.println(result);
        System.out.println("\n=== Demo Complete ===");
    }
}
