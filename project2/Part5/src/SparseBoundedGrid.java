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

import info.gridworld.grid.Grid;
import info.gridworld.grid.AbstractGrid;
import info.gridworld.grid.Location;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Iterator;
/**
 * A <code>SparseBoundedGrid</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid<E> extends AbstractGrid<E>
{
    // the array list store the row linked list
    private ArrayList<LinkedList> occupantArray; 
    private int numRows;
    private int numCols;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid
     * @param cols number of columns in SparseBoundedGrid
     */
    public SparseBoundedGrid(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
	}
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
	}

	numRows = rows;
	numCols = cols;
	
	occupantArray = new ArrayList<LinkedList>();
	for (int i = 0; i < numRows; i++) {

	    occupantArray.add(new LinkedList<OccupantInCol>());
	}

    }

    public int getNumRows()
    {
        return numRows;
    }

    public int getNumCols()
    {
	return numCols;
    }

    /**
     * test the location is valid or invalid
     * @return true or false
     */
    public boolean isValid(Location loc)
    {
        return 0 <= loc.getRow() && loc.getRow() < getNumRows()
                && 0 <= loc.getCol() && loc.getCol() < getNumCols();
    }

    /**
     *  return the location being occupied
     */
    public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> theLocations = new ArrayList<Location>();

        // Look at all grid locations.
        for (int r = 0; r < getNumRows(); r++) {

	    LinkedList<OccupantInCol> rowList = occupantArray.get(r);

	    /* if (rowList != null) {

		Iterator<OccupantInCol> iter = rowList.iterator();
		while (iter.hasNext()) {

		    Location loc = new Location(r, iter.next().getColumn());
		    theLocations.add(loc);
					    
		    }
		    }*/
	    if (rowList != null) {
		for (OccupantInCol occ : rowList) {

		    Location loc = new Location(r, occ.getColumn());
		    theLocations.add(loc);
		}
	    }
	}

        return theLocations;
    }

    public E get(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	}
	LinkedList<OccupantInCol> rowList =  occupantArray.get(loc.getRow());
	
	/*if (rowList != null) {
	    Iterator<OccupantInCol> iter = rowList.iterator();
	    while (iter.hasNext()) {

		if (iter.next().getColumn() == loc.getCol()) {
		    
		    return (E) iter.next().getOccupant();
		}
	    }
	}
	*/
	if (rowList != null) {
	    for (OccupantInCol occ : rowList) {
		    
		if (occ.getColumn() == loc.getCol()) {
			
		    return (E) occ.getOccupant();
		}
	    }
	}
	return null;
    }

    public E put(Location loc, E obj)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");
	}
        if (obj == null) {
            throw new NullPointerException("obj == null");
	}

        // Add the object to the grid.
        E oldOccupant = get(loc);
	LinkedList<OccupantInCol> rowList = occupantArray.get(loc.getRow());

	rowList.add(new OccupantInCol(obj, loc.getCol()));
        return oldOccupant;
    }

    /**
     * test the location, if it is valid then remove the object 
     * from the Location
     * @param loc the object Location
     * @return the removing object.
     */
    public E remove(Location loc)
    {
        if (!isValid(loc)) {
            throw new IllegalArgumentException("Location " + loc
                    + " is not valid");

	}
        
        // Remove the object from the grid.
        E r = get(loc);
	
	LinkedList<OccupantInCol> rowList = occupantArray.get(loc.getRow());

	if (rowList != null) {
	    Iterator<OccupantInCol> iter = rowList.iterator();
	    while (iter.hasNext()) {

		if (loc.getCol() == iter.next().getColumn()) {
		    iter.remove();
		    break;
		}
	    }
	
	}
	
        return r;
    }
}
