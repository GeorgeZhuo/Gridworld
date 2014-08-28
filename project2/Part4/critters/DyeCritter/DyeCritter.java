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
import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>DyeCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class DyeCritter extends Critter
{
    /**
     * default constructor with the green
     */
    public DyeCritter()
    {
        setColor(Color.GREEN);
    }

    /**
     * creat a DyeCritter with a DyeColor.
     */
    public DyeCritter(Color dyeColor) {

	setColor(dyeColor);
    }

      /**
       *  if the neighbor is not the same kind then, 
       *  changes this neighbor's color to be the
       * same as that dye critter's. If there are no neighbors, 
       * no action is taken.
     */
    public void processActors(ArrayList<Actor> actors)
    {
	Color color = getColor();
	for (Actor a : actors) {

	    if (!(a instanceof DyeCritter)) {

		a.setColor(color);
	    }
	}

    }

     /**
     * @return list of empty locations immediately to the SOUTH,
     * NORTH, EAST, WEST.
     * 
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
	
	Grid grid = getGrid();
        Location loc = getLocation();
        int[] dirs =
            { Location.SOUTH, Location.NORTH, Location.EAST, Location.WEST };

	// get the empty and valid location in the desire directions
        for (int d : dirs) {

            Location neighborLoc = loc.getAdjacentLocation(getDirection() + d);
            if (grid.isValid(neighborLoc) && grid.get(neighborLoc) == null) {

                locs.add(neighborLoc);
	    }
        }
     
        return locs;
    }
}
