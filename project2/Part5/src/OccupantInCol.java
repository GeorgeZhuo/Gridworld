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

/**
 * the OccupantInCol is the node for sparse list for
 * create the SparseBoundedGrid implements with the linkedlist
 */

public class OccupantInCol {

    /**
     * Constructor for create a new OccupantInCol node
     * with Object occ and column cols
     * @param occ the OccupantInCol Object
     * @param col the column
     */
    public OccupantInCol(Object occ, int col) {
	
	column = col;
	occupant = occ;
	
    }

    /**
     * @return the occupant
     */
    public Object getOccupant() {

	return occupant;
    }

    /**
     * @return the column number
     */
    public int getColumn() {
	
	return column;
    }

    /**
     * set the OccupantInCol node's Object
     * @param occ the Object to set
     */
    public void setOccupant(Object occ) {

	occupant = occ;
    }
    
    private Object occupant;
    private int column;
}
