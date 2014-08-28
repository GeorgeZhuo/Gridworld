import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains Z bugs. <br />
 * This class is not tested on the AP CS A and AB exams.
 * @version 1.0 2014-08-06
 * @author 12330443 zhuozhaojin
 */
final class ZBugRunner
{
    public static void main(String[] args)
    {
        ActorWorld world = new ActorWorld();

        ZBug bob = new ZBug(3);
        world.add(new Location(3, 5), bob);
        world.show();
    }

    // this is to prevent instantiation
    private ZBugRunner() {};
}
