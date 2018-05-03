/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dk.sdu.mmmi.cbse.main;

import dk.sdu.mmmi.cbse.common.services.IGamePluginService;
import java.util.Map;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author pershapakdast
 */
public class maintest {
        public static void main (String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"playerBeans.xml","enemyBeans.xml"});
        Map map = context.getBeansOfType(IGamePluginService.class);
        System.out.println(map);
        /*for (Object c : map.values()) {
            IGamePluginService a = (IGamePluginService) c;
            System.out.println(a.toString());
        }*/
    }
}
