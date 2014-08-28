import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;
import info.gridworld.grid.BoundedGrid;
import info.gridworld.actor.Actor;

import java.awt.Color;

/**
 * This class runs a world that contains circle  bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 * @version 1.0 2014-08-06
 * @author 12330443 zhuozhaojin
 */
final class CircleBugRunner {

    public static void main(String[] args)
    {
	BoundedGrid<Actor> grid = new BoundedGrid<Actor>(20, 20);
        ActorWorld world = new ActorWorld(grid);
        CircleBug alice = new CircleBug(2);
        alice.setColor(Color.ORANGE);
        CircleBug bob = new CircleBug(3);
        world.add(new Location(0, 7), alice);
        world.add(new Location(3, 5), bob);
        world.show();
    }

    // this to prevent intantiation
    private CircleBugRunner() {};
    
}
