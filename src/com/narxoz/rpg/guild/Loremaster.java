package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for lore, curses, and historical knowledge.
 * Open/closed proof: new colleague without modifying existing colleagues.
 */
public class Loremaster extends GuildMember {

    public Loremaster(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void shareLore(String topic, String payload) {
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        String sender = (from != null) ? from.getName() : "Council";
        System.out.println("  [Loremaster " + getName() + "] received topic='" + topic
                + "' from " + sender + ": " + payload);
        switch (topic) {
            case "lore":
                System.out.println("    -> Consulting ancient tomes for relevant knowledge.");
                break;
            case "curse":
                System.out.println("    -> Analyzing curse origin and possible countermeasures.");
                break;
            case "history":
                System.out.println("    -> Recalling historical records of similar events.");
                break;
            case "urgent":
                System.out.println("    -> URGENT: Searching archives for emergency protocols!");
                break;
            case "orders":
                System.out.println("    -> Recording orders in the guild chronicle.");
                break;
        }
    }
}
