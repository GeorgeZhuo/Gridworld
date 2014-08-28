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

import info.gridworld.grid.Grid;
import info.gridworld.grid.Location;

import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class QuickCrab extends CrabCritter
{
     /**
      * get the Location of QuickCrab in the right or left to it.
      * if the move location is empty and valid, add the Location
      * to the Location list
      * @return list of empty locations immediately to the right 
      * and to the left
     */
    public ArrayList<Location> getMoveLocations()
    {
        ArrayList<Location> locs = new ArrayList<Location>();
        int[] dirs = { Location.LEFT, Location.RIGHT };
	
        for (int dir : dirs) {
	    
	    Grid grid = getGrid();
	    Location loc = getLocation();
	    Location temp = loc.getAdjacentLocation(getDirection() + dir);
	    if(grid.isValid(temp) && grid.get(temp) == null) {
		Location loc2 = temp.getAdjacentLocation(getDirection() + dir);
		if(grid.isValid(loc2) && grid.get(loc2)== null) {
			locs.add(loc2);
		}
	    }

	}
	
	if (locs.size() == 0) {
	    return super.getMoveLocations();
	}

        return locs;
    }
    
}
