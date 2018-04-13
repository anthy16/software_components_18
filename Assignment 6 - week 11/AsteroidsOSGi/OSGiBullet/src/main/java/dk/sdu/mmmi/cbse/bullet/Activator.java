/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.bullet;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 * @author AKT
 */
public class Activator implements BundleActivator {
    
    public void start(BundleContext context) throws Exception {
        context.registerService(IEntityProcessingService.class, new BulletSystem(), null);
    }

    public void stop(BundleContext context) throws Exception {
    }
    
}
