import info.gridworld.actor.Bug;

/**
 * @version 1.0 2014-08-05
 * @author 12330443 zhuozhaojin
 */

/**
 * A <code>CircleBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class DancingBug extends Bug
{
    private int steps;
    private int[] danceTurn;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public DancingBug(int[] turns)
    {
	danceTurn = new int[turns.length];
	System.arraycopy(turns, 0, danceTurn, 0, turns.length);
        steps = 0;
	
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
	if (steps == danceTurn.length) {
	    steps = 0;
	}
	
	// dancing with integer times
	for (int i = 0; i < danceTurn[steps]; i++) {
	    turn();
	}
	steps++;
	super.act();
    }
}
