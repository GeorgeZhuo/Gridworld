import info.gridworld.actor.ActorWorld;
import info.gridworld.grid.Location;

import java.awt.Color;

/**
 * This class runs a world that contains dancing bug. <br />
 * This class is not tested on the AP CS A and AB exams.
 * @version 1.0 2014-08-06
 * @author 12330443 zhuozhaojin
 */
final class DancingBugRunner
{
    public static void main(String[] args)
    {
	final int a = 3;
	final int b = 5;
	int[] turns = {3, 4, 5, 6, 7};
	
        ActorWorld world = new ActorWorld();
	DancingBug bob = new DancingBug(turns);

        world.add(new Location(a, b), bob);
        world.show();
    }

    // this is to prevent instantiation
    private DancingBugRunner() {};
}
