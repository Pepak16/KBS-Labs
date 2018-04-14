/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.osgiplayer;

import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

/**
 *
 * @author pershapakdast
 */
public class Activator implements BundleActivator {
    
    public void start(BundleContext context) throws Exception {
        context.registerService(IGamePluginService.class.getName(), new PlayerPlugin(), null);
        context.registerService(IEntityProcessingService.class.getName(), new PlayerProcessor(), null);
    }
    
    public void stop(BundleContext context) throws Exception {
        //TODO add deactivation code here
    }
    
}
