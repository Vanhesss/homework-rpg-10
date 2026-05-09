package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for route reports and reconnaissance.
 */
public class Scout extends GuildMember {

    public Scout(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void reportRoute(String topic, String payload) {
        // TODO: send a scouting message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("  [Scout " + getName() + "] received topic='" + topic
                + "' from " + from.getName() + ": " + payload);
        switch (topic) {
            case "scouting":
                System.out.println("    -> Mapping the area and checking for ambushes.");
                break;
            case "orders":
                System.out.println("    -> Acknowledged order, preparing reconnaissance.");
                break;
            case "urgent":
                System.out.println("    -> URGENT: Deploying fast scouts immediately!");
                break;
        }
    }
}
