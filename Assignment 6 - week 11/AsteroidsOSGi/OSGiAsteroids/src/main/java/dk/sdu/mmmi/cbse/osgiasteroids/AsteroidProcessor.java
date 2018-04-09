package dk.sdu.mmmi.cbse.osgiasteroids;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.asteroids.IAsteroidSplitter;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;

public class AsteroidProcessor implements IEntityProcessingService {

    private IAsteroidSplitter asteroidSplitter;

    @Override
    public void process(GameData gameData, World world) {

       for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart positionPart = asteroid.getPart(PositionPart.class);
            MovingPart movingPart = asteroid.getPart(MovingPart.class);
            LifePart lifePart = asteroid.getPart(LifePart.class);
            
            
            int numPoints = 12;
            float speed = (float) Math.random() * 10f + 20f;
            if (lifePart.getLife() == 1) {
                numPoints = 8;
                speed = (float) Math.random() * 30f + 70f;
            } else if (lifePart.getLife()  == 2) {
                numPoints = 10;
                speed = (float) Math.random() * 10f + 50f;
            }
            movingPart.setSpeed(speed);
            movingPart.setUp(true);
           
         
            movingPart.process(gameData, asteroid);
            positionPart.process(gameData, asteroid);
            

            // Split event
            if (lifePart.isHit()) {
                asteroidSplitter.createSplitAsteroid(asteroid, world);
            }
            setShape(asteroid, numPoints);
        }

    }

    /**
     * Dependency Injection using OSGi Declarative Services
     */
    public void setAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = asteroidSplitter;
    }

    public void removeAsteroidSplitter(IAsteroidSplitter asteroidSplitter) {
        this.asteroidSplitter = null;
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


}
