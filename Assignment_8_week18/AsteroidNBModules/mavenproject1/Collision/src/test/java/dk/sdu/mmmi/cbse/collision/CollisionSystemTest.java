/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.collision;

import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.commonbullet.Bullet;
import dk.sdu.mmmi.cbse.commonenemy.Enemy;
import dk.sdu.mmmi.cbse.commonplayer.Player;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author AKT
 */
public class CollisionSystemTest {
    private Asteroid asteroid;
    private Player player;
    private World world;
    private GameData gd;
    
    public CollisionSystemTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        asteroid = new Asteroid();
        player = new Player();
        world = new World();
        gd = new GameData();
        
        asteroid.add(new LifePart(3));
        asteroid.add(new PositionPart(100,100,0));
        asteroid.setRadius(20);
        
        player.add(new LifePart(3));
        player.add(new PositionPart(100,100,0));
        player.setRadius(10);
        
        world.addEntity(asteroid);
        world.addEntity(player);
    }
    
    @After
    public void tearDown() {
        asteroid = null;
        player = null;
        world = null;
        gd = null;
    }

    /**
     * Test of process method, of class CollisionSystem.
     */
    @org.junit.Test
    public void testProcess() {
        System.out.println("process");
        
        CollisionSystem instance = new CollisionSystem();
        instance.process(gd, world);
        
        assertTrue(world.getEntities(Player.class).isEmpty());
    }
    
}
