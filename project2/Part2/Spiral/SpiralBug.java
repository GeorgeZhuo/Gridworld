import info.gridworld.actor.Bug;

/**
 * @version 1.0 2014-08-05
 * @author 12330443 zhuozhaojin
 */

/**
 * A <code>CircleBug</code> traces out a square "box" of a given size. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */

public class SpiralBug extends Bug
{
    private int steps;
    private int sideLength;

    /**
     * Constructs a box bug that traces a square of a given side length
     * @param length the side length
     */
    public SpiralBug(int length)
    {
        steps = 0;
        sideLength = length;
    }

    /**
     * Moves to the next location of the square.
     */
    public void act()
    {
        if (steps < sideLength && canMove()) {

            move();
            steps++;
        } else {

            turn();
	    turn();
	    sideLength++;
            steps = 0;
        }
    }
}
