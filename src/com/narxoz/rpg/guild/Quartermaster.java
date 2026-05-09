package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for gear, supplies, and rewards.
 */
public class Quartermaster extends GuildMember {

    public Quartermaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void requestSupplies(String topic, String payload) {
        // TODO: send a supply-related message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("  [Quartermaster " + getName() + "] received topic='" + topic
                + "' from " + from.getName() + ": " + payload);
        switch (topic) {
            case "supplies":
                System.out.println("    -> Preparing supply crates for the mission.");
                break;
            case "orders":
                System.out.println("    -> Acknowledged order, checking inventory.");
                break;
            case "rewards":
                System.out.println("    -> Counting gold and distributing rewards.");
                break;
            case "urgent":
                System.out.println("    -> URGENT: Rushing emergency supplies!");
                break;
        }
    }
}
