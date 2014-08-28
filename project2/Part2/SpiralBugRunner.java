import info.gridworld.grid.UnboundedGrid;
import info.gridworld.actor.Actor;
import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains circle  bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 * @version 1.0 2014-08-06
 * @author 12330443 zhuozhaojin
 */
final class SpiralBugRunner
{
    public static void main(String[] args)
    {
	UnboundedGrid<Actor> grid = new UnboundedGrid<Actor>();
        ActorWorld world = new ActorWorld(grid);
	SpiralBug alice = new SpiralBug(2);

        world.add(new Location(3, 5), alice);
        world.show();
    }

    // this is to prevent instantiation
    private SpiralBugRunner() {};
}
