import static org.junit.Assert.*;
import info.gridworld.actor.ActorWorld;
import info.gridworld.actor.Flower;
import info.gridworld.actor.Rock;
import info.gridworld.grid.Location;

import org.junit.Before;
import org.junit.Test;

public class JumperTest {
    private ActorWorld world = new ActorWorld();
    
    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testTurn() {
        Jumper jumper = new Jumper();
        jumper.turn();
        assertEquals(45, jumper.getDirection());
    }

    @Test
    public void testJump() {
        Jumper jumperNormal = new Jumper();
        Jumper jumperRock = new Jumper();
        Jumper jumperFlower = new Jumper();
        Jumper jumperJumper = new Jumper();

        //test normal
        world.add(new Location(2, 0), jumperNormal);
        jumperNormal.jump();
        assertEquals(new Location(0, 0), jumperNormal.getLocation());
        
        //test rock
        world.add(new Location(2, 2), jumperRock);
        world.add(new Location(1, 2), new Rock());
        jumperRock.jump();
        assertEquals(new Location(0, 2), jumperRock.getLocation());
        
        //test flower
        world.add(new Location(2, 3), jumperFlower);
        world.add(new Location(1, 3), new Flower());
        jumperFlower.jump();
        assertEquals(new Location(0, 3), jumperFlower.getLocation());
        
        //test jumper
        world.add(new Location(2, 4), jumperJumper);
        world.add(new Location(1, 4), new Jumper());
        jumperJumper.jump();
        assertEquals(new Location(0, 4), jumperJumper.getLocation());
    }

    @Test
    public void testCanJump() {
        Jumper jumperNormal = new Jumper();
        Jumper jumperEdge = new Jumper();
        Jumper jumperOutOfGrid = new Jumper();
        Jumper jumperRock = new Jumper();
        Jumper jumperFlower = new Jumper();
        Jumper jumperJumper = new Jumper();
        
        //test normal
        world.add(new Location(5, 5), jumperNormal);
        assertTrue(jumperNormal.canJump());
        
        //test edge
        world.add(new Location(0, 0), jumperEdge);
        assertFalse(jumperEdge.canJump());
        
        //test out of grid
        world.add(new Location(1, 1), jumperOutOfGrid);
        assertFalse(jumperOutOfGrid.canJump());
        
        //test rock
        world.add(new Location(2, 2), jumperRock);
        world.add(new Location(0, 2), new Rock());
        assertFalse(jumperRock.canJump());
        world.remove(new Location(0, 2));
        world.add(new Location(1, 2), new Rock());
        assertTrue(jumperRock.canJump());
        
        //test flower
        world.add(new Location(2, 3), jumperFlower);
        world.add(new Location(0, 3), new Flower());
        assertFalse(jumperFlower.canJump());
        world.remove(new Location(0, 3));
        world.add(new Location(1, 3), new Flower());
        assertTrue(jumperFlower.canJump());
        
        //test jumper
        world.add(new Location(2, 4), jumperJumper);
        world.add(new Location(0, 4), new Jumper());
        assertFalse(jumperJumper.canJump());
        world.remove(new Location(0, 4));
        world.add(new Location(1, 4), new Jumper());
        assertTrue(jumperJumper.canJump());
    }

}
