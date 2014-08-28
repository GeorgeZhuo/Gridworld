import info.gridworld.actor.Bug;
import info.gridworld.grid.Location;

/**
 * @version 1.0 2014-08-05
 * @author 12330443 zhuozhaojin
 */

/**
 * A <code>ZBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class ZBug extends Bug
{
    private int steps;
    private int sideLength;
    private int segment;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public ZBug(int length)
    {
        steps = 0;
	segment = 0;
        sideLength = length;
	setDirection(Location.EAST);
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
	    if (segment < 3 && steps < sideLength) {
		if (canMove()) {
		    move();
		    steps++;
		}
	    } else if (segment == 0) {
		
		setDirection(Location.SOUTHWEST);
		steps = 0;
		segment++;
	    } else if (segment == 1) {
		setDirection(Location.EAST);
		steps = 0;
		segment++;
	    }
    }
}
