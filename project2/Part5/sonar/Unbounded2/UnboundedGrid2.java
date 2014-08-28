/* 
 * AP(r) Computer Science GridWorld Case Study:
 * Copyright(c) 2002-2006 College Entrance Examination Board 
 * (http://www.collegeboard.com).
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
 * @author Alyce Brady
 * @author APCS Development Committee
 * @author Cay Horstmann
 */

import info.gridworld.grid.*;

import java.util.ArrayList;

/**
 * A <code>UnboundedGrid2</code> is a rectangular grid with a finite number of
 * numRows and numCols. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class UnboundedGrid2<E> extends AbstractGrid<E> 
{
    private Object[][] occupantArray; // the array storing the grid elements

    private int numRows;
    private int numCols;
    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>numRows > 0</code> and <code>cols > 0</code>.)
     * @param numRows number of numRows in UnboundedGrid2
     * @param cols number of numCols in UnboundedGrid2
     */
    public UnboundedGrid2()
    {
	numRows = 16;
	numCols = 16;

        occupantArray = new Object[numRows][numCols];
    }
    
     public int getNumRows()
    {
        return -1;
    }

    public int getNumCols()
    {
        return -1;
    }

    public boolean isValid(Location loc)
    {
        return (loc.getRow() >= 0) && (loc.getCol() >= 0);
    }

    /**
     * return the list location which been occupied by the Object
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < numRows; r++)
        {
            for (int c = 0; c < numCols; c++)
            {
                // If there's an object at this location, put it in the array.
                Location loc = new Location(r, c);
                if (get(loc) != null)
                    theLocations.add(loc);
            }
        }

        return theLocations;
    }

    /**
     * get the Object by the given Location
     * if the Location is not outside the grid bound, return null.
     * @param loc the Location of the Object
     */
    public E get(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	if (loc.getRow() < numRows && loc.getCol() < numCols) {
	     // unavoidable warning
	    return (E) occupantArray[loc.getRow()][loc.getCol()];

	} else {
	    return null;
	}
    }

    /**
     * put the Object into the grid by given the Location and the Object
     * if the given Location is outside the grid bound, then double the
     * the grid bound.
     * @param loc the Location of the Object
     * @param the Object to be put
     */
    public E put(Location loc, E obj)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        if (obj == null)
            throw new NullPointerException("obj == null");

        // Add the object to the grid.
        E oldOccupant = get(loc);
	int dime = numRows;

	// expand the array by double the array bound
	if (loc.getRow() >= numRows || loc.getCol() >= numCols) {
	    
	    while (loc.getRow() >= dime || loc.getCol() >= dime)
		dime *= 2;

	    Object[][] temp = new Object[dime][dime];

	    for (int r = 0; r < numRows; r++) {
		
		for (int c = 0; c < numCols; c++) {
		    
		    temp[r][c] = occupantArray[r][c];
		}
	    }

	    occupantArray = temp;
	    numRows = dime;
	    numCols = dime;
	}

        occupantArray[loc.getRow()][loc.getCol()] = obj;
        return oldOccupant;
    }

    /**
     * remove the Object by given the Location and return the Object
     * if the Location is in the grid return the Object, otherwise
     * return null
     * @param loc the Location of the Object
     */
    public E remove(Location loc)
    {
        if (!isValid(loc))
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
        
        // Remove the object from the grid.
        E r = get(loc);
	if (loc.getRow() < numRows && loc.getCol() < numCols) {

	    occupantArray[loc.getRow()][loc.getCol()] = null;
	    return r;
	} else {

	    return null;
	}
    }
}
