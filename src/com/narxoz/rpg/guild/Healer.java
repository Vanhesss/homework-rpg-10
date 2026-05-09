package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for wounds, potions, and recovery plans.
 */
public class Healer extends GuildMember {

    public Healer(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void prepareAid(String topic, String payload) {
        // TODO: send a healing message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = (from != null) ? from.getName() : "Council";
        System.out.println("  [Healer " + getName() + "] received topic='" + topic
                + "' from " + sender + ": " + payload);
        switch (topic) {
            case "healing":
                System.out.println("    -> Preparing healing potions and bandages.");
                break;
            case "scouting":
                System.out.println("    -> Noted scouting report, preparing field medkits.");
                break;
            case "urgent":
                System.out.println("    -> URGENT: Setting up emergency triage station!");
                break;
        }
    }
}
