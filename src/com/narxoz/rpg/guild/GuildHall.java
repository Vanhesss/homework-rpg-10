package com.narxoz.rpg.guild;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topic-based mediator for the Adventurers' Guild war council.
 */
public class GuildHall implements GuildMediator {

    private final Map<String, List<GuildMember>> membersByTopic = new HashMap<>();

    private int totalMessagesRouted = 0;
    private int totalMembersNotified = 0;

    @Override
    public void register(GuildMember member) {
        if (member instanceof Quartermaster) {
            addSubscriber("supplies", member);
            addSubscriber("orders", member);
            addSubscriber("rewards", member);
            addSubscriber("urgent", member);
        } else if (member instanceof Scout) {
            addSubscriber("scouting", member);
            addSubscriber("orders", member);
            addSubscriber("urgent", member);
        } else if (member instanceof Healer) {
            addSubscriber("healing", member);
            addSubscriber("urgent", member);
            addSubscriber("scouting", member);
        } else if (member instanceof Captain) {
            addSubscriber("orders", member);
            addSubscriber("supplies", member);
            addSubscriber("scouting", member);
            addSubscriber("healing", member);
            addSubscriber("urgent", member);
            addSubscriber("rewards", member);
        } else {
            addSubscriber("orders", member);
            addSubscriber("urgent", member);
        }
    }

    @Override
    public void dispatch(String topic, GuildMember from, String payload) {
        List<GuildMember> subscribers = subscribersFor(topic);
        totalMessagesRouted++;
        for (GuildMember member : subscribers) {
            if (member != from) {
                member.receive(topic, from, payload);
                totalMembersNotified++;
            }
        }
    }

    public int getTotalMessagesRouted() {
        return totalMessagesRouted;
    }

    public int getTotalMembersNotified() {
        return totalMembersNotified;
    }

    protected void addSubscriber(String topic, GuildMember member) {
        membersByTopic.computeIfAbsent(topic, key -> new ArrayList<>()).add(member);
    }

    protected List<GuildMember> subscribersFor(String topic) {
        return membersByTopic.getOrDefault(topic, List.of());
    }
}
