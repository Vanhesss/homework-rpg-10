package com.narxoz.rpg.quest;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Traverses quests sorted by reward amount (ascending).
 * Open/closed proof: new traversal without modifying existing iterators.
 */
public class RewardSortedQuestIterator implements QuestIterator {

    private final List<Quest> snapshot;
    private int cursor;

    public RewardSortedQuestIterator(QuestLog questLog) {
        this.snapshot = new ArrayList<>(questLog.snapshot());
        this.snapshot.sort(Comparator.comparingInt(Quest::getRewardGold));
        this.cursor = 0;
    }

    @Override
    public boolean hasNext() {
        return cursor < snapshot.size();
    }

    @Override
    public Quest next() {
        return snapshot.get(cursor++);
    }
}
