/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.asteroid;

import dk.sdu.mmmi.cbse.commonasteroid.Asteroid;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Random;
import org.openide.util.lookup.ServiceProvider;
import org.openide.util.lookup.ServiceProviders;

@ServiceProviders(value = {
    @ServiceProvider(service = IEntityProcessingService.class)
    , 
    @ServiceProvider(service = IGamePluginService.class)}
)

/**
 *
 * @author AKT
 */
public class AsteroidControlSystem implements IEntityProcessingService, IGamePluginService {
    
    private Entity asteroid;

    @Override
    public void process(GameData gameData, World world) {

       for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);
            
            
            int numPoints = 12;
            movingPart.setUp(true);
           
         
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            
            setShape(asteroid, numPoints);
        }

    }

    private void setShape(Entity entity, int numPoints) {
        float[] shapex = entity.getShapeX();
        float[] shapey = entity.getShapeY();
        PositionPart positionPart = entity.getPart(PositionPart.class);
        float x = positionPart.getX();
        float y = positionPart.getY();
        float radians = positionPart.getRadians();

        shapex[0] = (float) (x + Math.cos(radians) * 20);
        shapey[0] = (float) (y + Math.sin(radians) * 20);

        shapex[1] = (float) (x + Math.cos(radians + 3.1415f * 0.5) * 30);
        shapey[1] = (float) (y + Math.sin(radians + 3.1415f) * 30 * 0.5);

        shapex[2] = (float) (x + Math.cos(radians + 3.1415f) * 30);
        shapey[2] = (float) (y + Math.sin(radians + 3.1415f) * 30);

        shapex[3] = (float) (x + Math.cos(radians + 3.1415f * 1.5) * 30);
        shapey[3] = (float) (y + Math.sin(radians + 3.1415f * 1.5) * 30);

        entity.setShapeX(shapex);
        entity.setShapeY(shapey);
    }
    
    @Override
    public void stop(GameData gameData, World world) {
        // Remove entities
        world.removeEntity(asteroid);
    }
    
    @Override
    public void start(GameData gameData, World world) {
        
        // Add entities to the world
        asteroid = createAsteroid(gameData);
        world.addEntity(asteroid);
    }
    
    private Entity createAsteroid(GameData gameData) {
        Random rdm = new Random();

        float deacceleration = 10;
        float acceleration = 100;
        float maxSpeed = 40;
        float rotationSpeed = 5;
        float x = gameData.getDisplayWidth() / 2;
        float y = gameData.getDisplayHeight() / 2;
        float radians = rdm.nextFloat();
        
        Entity asteroidEntity = new Asteroid();
        asteroidEntity.add(new MovingPart(deacceleration, acceleration, maxSpeed, rotationSpeed));
        asteroidEntity.add(new PositionPart(x, y, radians));
        
        return asteroidEntity;
    }
}
