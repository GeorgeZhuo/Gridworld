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
import java.util.*;


/**
 * A <code>SparseBoundedGrid2</code> is a rectangular grid with a finite number of
 * rows and columns. <br />
 * The implementation of this class is testable on the AP CS AB exam.
 */
public class SparseBoundedGrid2<E> extends AbstractGrid<E>
{
    // the Map to store the object with key is Location
    // and the item is the Object E
    private Map<Location, E> occupantMap;

    private int numRows;
    private int numCols;

    /**
     * Constructs an empty bounded grid with the given dimensions.
     * (Precondition: <code>rows > 0</code> and <code>cols > 0</code>.)
     * @param rows number of rows in SparseBoundedGrid2
     * @param cols number of columns in SparseBoundedGrid2
     */
    public SparseBoundedGrid2(int rows, int cols)
    {
        if (rows <= 0) {
            throw new IllegalArgumentException("rows <= 0");
	}
        if (cols <= 0) {
            throw new IllegalArgumentException("cols <= 0");
	}

	numRows = rows;
	numCols = cols;

	occupantMap = new HashMap<Location, E>();
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

   public ArrayList<Location> getOccupiedLocations()
    {
        ArrayList<Location> a = new ArrayList<Location>();
        for (Location loc : occupantMap.keySet()) {

            a.add(loc);
	}

        return a;
    }

   /**
    * get the object E by given the Location
    * @param loc the location of Object
    */
    public E get(Location loc)
    {
        if (loc == null) {

            throw new NullPointerException("loc == null");
	}
        return occupantMap.get(loc);
    }

    /**
     * put the object E to the given Location
     * @param loc the location of Object
     * @param obj the Object E to put into grid
     */
    public E put(Location loc, E obj)
    {
        if (loc == null) {
            throw new NullPointerException("loc == null");
	}
        if (obj == null) {
            throw new NullPointerException("obj == null");
	}

        return occupantMap.put(loc, obj);
    }

    /**
     * remove the Object by the given Location
     * @param loc the Location of object
     */
    public E remove(Location loc)
    {
        if (loc == null) {

            throw new NullPointerException("loc == null");
	}
        return occupantMap.remove(loc);
    }
}
