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

import java.lang.Math;
import java.awt.Color;
import java.util.ArrayList;

/**
 * A <code>CrabCritter</code> looks at a limited set of neighbors when it eats and moves.
 * <br />
 * This class is not tested on the AP CS A and AB exams.
 */
public class KingCrab extends CrabCritter
{
    public KingCrab() {

	setColor(Color.YELLOW);
    }
    /**
     * get the neighbor actor like the CrabCritter,
     * if the actor can move to a Location away, then move
     * otherwise, remove the actor from the grid.
     * @param actors the neighbor actors
     */
    public void processActors(ArrayList<Actor> actors)
    {
        int n = actors.size();
        if (n == 0) {
            return;
	}
	
        for (Actor a : actors) {
	    if (!moveAway(a)) {
		
		a.removeSelfFromGrid();
	    }
		
	}
    }
    
    /**
     * if get the adjacent empty location of actor, if the location 
     * is away from the KingCrab, the actor move to it, and return
     * true, else it return false.
     * @param a is the actor
     */
    public boolean moveAway(Actor a) {

	ArrayList<Location> locs = getGrid().getEmptyAdjacentLocations
	    (a.getLocation());

	Location curLoc = getLocation();
	
	for (Location loc : locs) {

	    double x = curLoc.getRow() - loc.getRow();
	    double y = curLoc.getCol() - loc.getCol();
	    double dist = Math.sqrt(x * x + y * y);
	    if (dist > 1) {
		a.moveTo(loc);
		return true;
	    }
	}

	return false;
    }
}
