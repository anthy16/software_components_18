/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgicollision;

import dk.sdu.mmmi.cbse.common.asteroids.Asteroid;
import dk.sdu.mmmi.cbse.common.bullet.Bullet;
import dk.sdu.mmmi.cbse.common.data.Entity;
import dk.sdu.mmmi.cbse.common.data.GameData;
import dk.sdu.mmmi.cbse.common.data.World;
import dk.sdu.mmmi.cbse.common.data.entityparts.LifePart;
import dk.sdu.mmmi.cbse.common.data.entityparts.MovingPart;
import dk.sdu.mmmi.cbse.common.data.entityparts.PositionPart;
import dk.sdu.mmmi.cbse.common.enemy.Enemy;
import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import dk.sdu.mmmi.cbse.osgicommonplayer.Player;

/**
 *
 * @author AKT
 */
public class CollisionSystem implements IPostEntityProcessingService {

    private int radius;

    @Override
    public void process(GameData gameData, World world) {

        for (Entity entity : world.getEntities()) {

            PositionPart positionPart = entity.getPart(PositionPart.class);

            int x = (int) positionPart.getX();
            int y = (int) positionPart.getY();
            this.radius = (int) entity.getRadius();

            if (entity.getClass() == Player.class) {
                this.checkPlayerCollision(entity, x, y, world);
            } else if (entity.getClass() == Asteroid.class) {
                this.checkAsteroidCollision(entity, x, y, world);
            } else if (entity.getClass() == Enemy.class) {
                this.checkEnemyCollision(entity, y, y, world);
            }

        }
    }

    /*
    *   Checks possible collision events for the player entity.
    *   Runs possible collision events in case of collision.
    *   
    *   @param  player  the player entity
    *   @param  entityX the x value of the player entity
    *   @param  entityY the y value of the player entity
    *   @param  world   the game world
     */
    private void checkPlayerCollision(Entity player, int entityX, int entityY, World world) {
        LifePart playerHP = player.getPart(LifePart.class);

        // Bullet check
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart collidingEntityPositionPart = bullet.getPart(PositionPart.class);

            int x = (int) collidingEntityPositionPart.getX();
            int y = (int) collidingEntityPositionPart.getY();
            int collidingEntityRadius = (int) bullet.getRadius();

            int currentDistance = (int) Math.sqrt(Math.pow((entityX - x), 2) + Math.pow((entityY - y), 2));

            if ((int) (this.radius + collidingEntityRadius) > currentDistance) {
                playerHP.setIsHit(true);

                if (playerHP.isDead()) {
                    world.removeEntity(player);
                }

                System.out.println("Player hit by bullet! " + playerHP.getLife());
            }
        }

        //Asteroid check
        for (Entity asteroid : world.getEntities(Asteroid.class)) {
            PositionPart collidingEntityPositionPart = asteroid.getPart(PositionPart.class);

            int x = (int) collidingEntityPositionPart.getX();
            int y = (int) collidingEntityPositionPart.getY();
            int collidingEntityRadius = (int) asteroid.getRadius();

            int currentDistance = (int) Math.sqrt(Math.pow((entityX - x), 2) + Math.pow((entityY - y), 2));

            if ((int) (this.radius + collidingEntityRadius) > currentDistance) {
                world.removeEntity(player);

                System.out.println("CRASH! Player hit by asteroid!");
            }
        }
    }

    /*
    *   Checks possible collision events for the asteroid entity.
    *   Runs possible collision events in case of collision.
    *   
    *   @param  asteroid    the asteroid entity
    *   @param  asteroidX   the x value of the asteroid entity
    *   @param  asteroidY   the y value of the asteroid entity
    *   @param  world       the game world
     */
    private void checkAsteroidCollision(Entity asteroid, int asteroidX, int asteroidY, World world) {
        LifePart lifePart = asteroid.getPart(LifePart.class);

        // Bullet collision
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart collidingEntityPositionPart = bullet.getPart(PositionPart.class);

            int x = (int) collidingEntityPositionPart.getX();
            int y = (int) collidingEntityPositionPart.getY();
            int collidingEntityRadius = (int) bullet.getRadius();

            int currentDistance = (int) Math.sqrt(Math.pow((asteroidX - x), 2) + Math.pow((asteroidY - y), 2));

            if ((int) (this.radius + collidingEntityRadius) > currentDistance) {
                lifePart.setIsHit(true);
                System.out.println("Asteroid is hit!");
            }
        }
    }

    /*
    *   Checks possible collision events for the enemy entity.
    *   Runs possible collision events in case of collision.
    *   
    *   @param  enemy   the enemy entity
    *   @param  enemyX  the x value of the enemy entity
    *   @param  enemyY  the y value of the enemy entity
    *   @param  world   the game world
     */
    private void checkEnemyCollision(Entity enemy, int enemyX, int enemyY, World world) {
        LifePart enemyHP = enemy.getPart(LifePart.class);

        // Bullet collision
        for (Entity bullet : world.getEntities(Bullet.class)) {
            PositionPart collidingEntityPositionPart = bullet.getPart(PositionPart.class);

            int x = (int) collidingEntityPositionPart.getX();
            int y = (int) collidingEntityPositionPart.getY();
            int collidingEntityRadius = (int) bullet.getRadius();

            int currentDistance = (int) Math.sqrt(Math.pow((enemyX - x), 2) + Math.pow((enemyY - y), 2));

            if ((int) (this.radius + collidingEntityRadius) > currentDistance) {
                enemyHP.setIsHit(true);

                if (enemyHP.isDead()) {
                    world.removeEntity(enemy);
                }

                System.out.println("Enemy hit by bullet! " + enemyHP.getLife());
            }
        }
    }
}
