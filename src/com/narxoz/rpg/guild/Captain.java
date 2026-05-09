package com.narxoz.rpg.guild;

/**
 * Guild officer responsible for orders and mission coordination.
 */
public class Captain extends GuildMember {

    public Captain(String name, GuildMediator mediator) {
        super(name, mediator);
    }

    public void issueOrder(String topic, String payload) {
        // TODO: send a command message through the mediator.
        getMediator().dispatch(topic, this, payload);
    }

    @Override
    public void receive(String topic, GuildMember from, String payload) {
        System.out.println("  [Captain " + getName() + "] received topic='" + topic
                + "' from " + from.getName() + ": " + payload);
        switch (topic) {
            case "orders":
                System.out.println("    -> Reviewing tactical orders.");
                break;
            case "supplies":
                System.out.println("    -> Noted supply status for mission planning.");
                break;
            case "scouting":
                System.out.println("    -> Analyzing scout report for battle strategy.");
                break;
            case "healing":
                System.out.println("    -> Adjusting formation based on healer capacity.");
                break;
            case "urgent":
                System.out.println("    -> URGENT: Issuing emergency battle formations!");
                break;
            case "rewards":
                System.out.println("    -> Approving reward distribution plan.");
                break;
        }
    }
}
