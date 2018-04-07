/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.enemy;

//import dk.sdu.mmmi.cbse.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;

/**
 *
 * @author AKT
 */
public class EnemyCollisionSystem implements IPostEntityProcessingService {
    
    private int radius;

    @Override
    public void process(GameData gameData, World world) {
        /*for (Entity enemy : world.getEntities(Enemy.class)) {
            PositionPart positionPart = enemy.getPart(PositionPart.class);
            LifePart lifePart = enemy.getPart(LifePart.class);

            int x1 = (int)positionPart.getX();
            int y1 = (int)positionPart.getY();
            this.radius = (int)enemy.getRadius();
            
            for(Entity bullet : world.getEntities(Bullet.class)) {
                PositionPart asteroidPositionPart = bullet.getPart(PositionPart.class);
                
                int x2 = (int)asteroidPositionPart.getX();
                int y2 = (int)asteroidPositionPart.getY();
                int bulletRadius = (int)bullet.getRadius();
                
                int currentDistance = (int) Math.sqrt(Math.pow((x1-x2), 2) + Math.pow((y1-y2),2));
                
                if((int)(this.radius + bulletRadius) > currentDistance && lifePart.getLife() < 0) {
                    System.out.println("DEAD! " + x1 + " " + x2 + " " + y1 + " " + y2);
                    System.out.println((int)(this.radius + bulletRadius));
                    System.out.println(currentDistance);
                    
                    world.removeEntity(enemy);
                    world.removeEntity(bullet);
                } else if((int)(this.radius + bulletRadius) > currentDistance) {
                    System.out.println("HIT! " + x1 + " " + x2 + " " + y1 + " " + y2);
                    lifePart.setLife(lifePart.getLife() - 1);
                    world.removeEntity(bullet);
                }
            }*/
        //}

    }
}
