/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2005-2006 Cay S. Horstmann (http://horstmann.com)
 *
 * This code is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * @author Chris Nevison
 * @author Barbara Cloud Wells
 * @author Cay Horstmann
 */

import info.gridworld.actor.Actor;
import info.gridworld.actor.Critter;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>BlusterCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class BlusterCritter extends Critter
{
    public BlusterCritter(int number) {
	
	super();
	changeNumber = number;
    }
    
    /**
     * A BlusterCritter looks at all of the neighbors within two steps 
     * of its current location. (For a BlusterCritter not near an edge, 
     * this includes 24 locations). It counts the number of critters 
     * in those locations. If there are fewer than c critters, the
     * BlusterCritter's color gets brighter (color values increase).
     * If there are c or more critters, the BlusterCritter's color darkens
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int critterNumber = 0;

	for (Actor a : actors) {
	    if (a instanceof Critter) {
		critterNumber++;
	    }
	   
	}
	if (critterNumber < changeNumber) {
	    
	    Color color = getColor();
	    int red = color.getRed();
	    int green = color.getGreen(); 
	    int blue = color.getBlue();
	    
	    if (red < 255) {
		red++;
	    }
	    if (green < 255) {
		green++;
	    }
	    if (blue < 255) {
		blue++;
	    }
	    setColor(new Color(red, green, blue));

	} else {
	    
	    Color color = getColor();
	    int red = color.getRed();
	    int green = color.getGreen(); 
	    int blue = color.getBlue();
	    
	    if (red > 0) {
		red--;
	    }
	    if (green > 0) {
		green--;
	    }
	    if (blue > 0) {

		blue--;
	    }
	    
	    setColor(new Color(red, green, blue));
	}
    }
 
    
    /**
     * A bluster gets the actors within two steps of its current position
     * includes 24 locations
     * @return a list of actors occupying these locations
     */
    public ArrayList<Actor> getActors()
    {
	Location loc = getLocation();
        ArrayList<Actor> actors = new ArrayList<Actor>();
	int rows = loc.getRow();
	int cols = loc.getCol();
	for (int row = rows - 2; row <= rows + 2; row++) {
	    for (int col = cols - 2; col <= cols + 2; col++) {

		Location temp = new Location(row, col);
		if (getGrid().isValid(temp)) {
		    Actor a = getGrid().get(temp);
		    if (a != null && a != this) {
			actors.add(a);
		    }
		}
	    }
	}

        return actors;
    }

    private int changeNumber;

}
