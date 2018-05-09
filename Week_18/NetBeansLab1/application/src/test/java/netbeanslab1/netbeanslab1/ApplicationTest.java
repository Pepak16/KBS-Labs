package netbeanslab1.netbeanslab1;


import dk.sdu.mmmi.cbse.common.services.IEntityProcessingService;
import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.io.IOException;
import static java.nio.file.Files.copy;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.Paths.get;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import junit.framework.Test;
import org.netbeans.junit.NbModuleSuite;
import org.netbeans.junit.NbTestCase;
import org.openide.util.Lookup;

public class ApplicationTest extends NbTestCase {

    private static final String ADD_ENTITY_UPDATES_FILE = "/Users/pershapakdast/NetBeansProjects/KBS-Labs/Week_18/NetBeansLab1/application/src/test/resources/addentities/updates.xml";
    private static final String REM_ENTITY_UPDATES_FILE = "/Users/pershapakdast/NetBeansProjects/KBS-Labs/Week_18/NetBeansLab1/application/src/test/resources/removeentities/updates.xml";
    private static final String UPDATES_FILE = "/Users/pershapakdast/NetBeansProjects/KBS-Labs/Week_18/NetBeansLab1/netbeans_site/updates.xml";
    
    public static Test suite() {
        return NbModuleSuite.createConfiguration(ApplicationTest.class).
                gui(false).
                failOnMessage(Level.WARNING). // works at least in RELEASE71
                failOnException(Level.INFO).
                enableClasspathModules(false). 
                clusters(".*").
                suite(); // RELEASE71+, else use NbModuleSuite.create(NbModuleSuite.createConfiguration(...))
    }

    public ApplicationTest(String n) {
        super(n);
    }

    public void testApplication() throws InterruptedException, IOException {
        // pass if there are merely no warnings/exceptions
        /* Example of using Jelly Tools (additional test dependencies required) with gui(true):
        new ActionNoBlock("Help|About", null).performMenu();
        new NbDialogOperator("About").closeByButton();
         */
        
        List<IEntityProcessingService> processors = new CopyOnWriteArrayList<>();
        List<IGamePluginService> plugins = new CopyOnWriteArrayList<>();
        waitForUpdate(processors, plugins);
        
       
        //Size of installed plugins and processors are 0, because no modules are installed here
        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No processors", 0, processors.size());
        
        //Loads enemy
        copy(get(ADD_ENTITY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        //When enemy is loaded
        assertEquals("One plugins", 2, plugins.size());
        assertEquals("One processors", 2, processors.size());
        
        //Unloads enemy
        copy(get(REM_ENTITY_UPDATES_FILE), get(UPDATES_FILE), REPLACE_EXISTING);
        waitForUpdate(processors, plugins);
        
        //When enemy is unloaded
        assertEquals("No plugins", 0, plugins.size());
        assertEquals("No processors", 0, processors.size());
        
    }

    private void waitForUpdate(List<IEntityProcessingService> processors, List<IGamePluginService> plugins) throws InterruptedException {
        Thread.sleep(10000);
        processors.clear();
        processors.addAll(Lookup.getDefault().lookupAll(IEntityProcessingService.class));
        plugins.clear();
        plugins.addAll(Lookup.getDefault().lookupAll(IGamePluginService.class));
    }
    
}
