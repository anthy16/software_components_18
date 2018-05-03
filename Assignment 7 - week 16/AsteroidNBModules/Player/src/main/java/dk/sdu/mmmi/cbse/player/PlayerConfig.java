/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.player;

import dk.sdu.mmmi.cbse.commonbullet.BulletSPI;
import org.openide.util.Lookup;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author AKT
 */
@Configuration
public class PlayerConfig {
    private final Lookup lookup = Lookup.getDefault();
    
    @Bean
    public BulletSPI BulletSystem() {
        return lookup.lookup(BulletSPI.class);
    }
}
