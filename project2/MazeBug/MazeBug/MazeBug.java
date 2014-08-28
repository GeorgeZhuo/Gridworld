
import info.gridworld.actor.Actor;
import info.gridworld.actor.Bug;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * A <code>MazeBug</code> can find its way in a maze. <br />
 * The implementation of this class is testable on the AP CS A and AB exams.
 */
public class MazeBug extends Bug {

    public Location next;
    public Location last;
    public boolean isEnd = false;
    public boolean isPush = false;
    public Integer stepCount = 0;
    boolean hasShown = false;//final message has been shown

    public Stack<ArrayList<Location>> crossLocation 
	= new Stack<ArrayList<Location>>();
    
    public Map<Integer, Integer> dirCounter = 
	new HashMap<Integer, Integer>();
    /**
     * Constructs a box bug that traces a square of a given side length
     */
    public MazeBug() {

	setColor(Color.GREEN);
	last = new Location(0, 0);

	dirCounter.put(Location.NORTH, 0);
	dirCounter.put(Location.SOUTH, 0);
	dirCounter.put(Location.EAST, 0);
	dirCounter.put(Location.WEST, 0);

    }

    /**
     * Moves to the next location of the square.
     */
    public void act() {

	boolean willMove = canMove();
	if (isEnd == true) {
	    //to show step count when reach the goal		
	    if (hasShown == false) {
		String msg = stepCount.toString() + " steps";
		JOptionPane.showMessageDialog(null, msg);
		hasShown = true;
	    }
	} else if (willMove) {
	    
	    move();
	    //increase step count when move 
	    stepCount++;
	} else {
	    
	    back();
	    stepCount++;
	}

	System.out.println("Hello MazeWorld");
	System.out.printf("%d, %d, %d, %d\n", dirCounter.get(Location.NORTH), 
			  dirCounter.get(Location.SOUTH), dirCounter.get(Location.EAST), 
			  dirCounter.get(Location.WEST));
    }

    /**
     * Find all positions that can be move to.
     * 
     * @param loc
     *            the location to detect.
     * @return List of positions.
     */
    public ArrayList<Location> getValid(Location loc) {

	Grid<Actor> grid = getGrid();
	if (grid == null)
	    return null;

	ArrayList<Location> valid = new ArrayList<Location>();
	ArrayList<Location> lastNode = crossLocation.peek();
	int[] dirs =
            { Location.SOUTH, Location.NORTH, Location.EAST, Location.WEST };

	// get the empty and valid location in the desire directions
        for (int d : dirs) {

            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
	    if (!lastNode.contains(neighborLoc) && grid.isValid(neighborLoc)) {
		
		Actor actor = (Actor) grid.get(neighborLoc);
		if (grid.get(neighborLoc) == null || actor instanceof Flower) {
		    
		    valid.add(neighborLoc);
		} 
	
		if (actor instanceof Rock && actor.getColor() == Color.RED) {
		
		    isEnd = true;
		}
	    }
        }
     
	return valid;
    }

    /**
     * Tests whether this bug can move forward into a location that is empty or
     * contains a flower.
     * 
     * @return true if this bug can move.
     */
    public boolean canMove() {

	ArrayList<Location> moveLocs = getValid(getLocation());

	if (isEnd == true) {
	    return false;
	} 
	if (!moveLocs.isEmpty()) {

	    if (moveLocs.size() > 1) {
		isPush = true;
	    }

	    selectMoveLocation(moveLocs);
	    return true;

	} else {
	    return false;
	}
    }
     
    /**
     * move to the next valid Location, and reset the lastnode of
     * the stack, put the 
     */
    public void move() {

	Grid<Actor> gr = getGrid();
	if (gr == null) {

	    return;
	}

	Location loc = getLocation();
	if (gr.isValid(next)) {

	    setDirection(loc.getDirectionToward(next));
	    moveTo(next);

	    int counter = dirCounter.get(getDirection());
	    dirCounter.put(getDirection(), ++counter);

	    if (isPush) {
		
		//reset the node of previously occupied location
		ArrayList<Location> lastNode = crossLocation.pop();
		lastNode.add(next);
		crossLocation.push(lastNode);	
		
		//push the node  of current location
		ArrayList<Location> currentNode = new ArrayList<Location>();
		currentNode.add(getLocation());
		currentNode.add(loc);

		crossLocation.push(currentNode);

		isPush = false;
	    }

	} else {
	    removeSelfFromGrid();
	}

	Flower flower = new Flower(getColor());
	flower.putSelfInGrid(gr, loc);
	
	last = loc;
		
    }

    /**
     * if the the stack is no empty, go back to the last node
     * decrease the probability of the direction.
     * and left a Flower on the old Location
     */
    public void back() {

	Grid<Actor> gr = getGrid();
	if (gr == null)
	    return;

	Location loc = getLocation();
	
	if (gr.isValid(last)) {

	    setDirection(loc.getDirectionToward(last));
	    moveTo(last);

	} else {
	    removeSelfFromGrid();
	}
	
	int counter = dirCounter.get(getDirection());
	dirCounter.put(getDirection(), --counter);

	Flower flower = new Flower(getColor());
	flower.putSelfInGrid(gr, loc);
	
	last = loc;
    }

    /**
     * Select the location with the largest probability
     * @param locs the list valid location 
     */
    public void selectMoveLocation(ArrayList<Location> locs) {


	int size = locs.size();
	Location loc = getLocation();
	int maxItem = dirCounter.get(loc.getDirectionToward(locs.get(0)));
	int maxIndex = 0;
	for (int i = 1; i < size; i++) {

	    int temp = dirCounter.get(loc.getDirectionToward(locs.get(i)));
	    if (maxItem < temp) {
		maxItem = temp;
		maxIndex = i;
	    }
	}

	next = locs.get(maxIndex);
    }
}
