package dk.sdu.mmmi.cbse.osgicollision;

import dk.sdu.mmmi.cbse.common.services.IPostEntityProcessingService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

    public void start(BundleContext context) throws Exception {
        context.registerService(IPostEntityProcessingService.class, new CollisionSystem(), null);
    }

    public void stop(BundleContext context) throws Exception {
    }

}
