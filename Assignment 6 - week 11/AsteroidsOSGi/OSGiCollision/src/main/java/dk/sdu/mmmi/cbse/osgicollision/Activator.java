/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgicollision;

import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.framework.ServiceRegistration;

/**
 *
 * @author AKT
 */
public class Activator implements BundleActivator {
    
    private ServiceRegistration registration;
    
    public void start(BundleContext context) throws Exception {
        registration = context.registerService(IPostEntityProcessingService.class, new CollisionSystem(), null);
    }
    
    public void stop(BundleContext context) throws Exception {
        registration.unregister();
    }
    
}
