/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package antdt.listener;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Web application lifecycle listener.
 *
 * @author HP
 */
public class MyContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext context = sce.getServletContext();
        String realPath = context.getRealPath("/") + "WEB-INF/roadMap.properties";
        String realPath1  = "/WEB-INF/roadMap.properties";
        File file = new File(realPath1);
        Map<String, String> roadMap = new HashMap<>();
        InputStreamReader isr = null;
        BufferedReader br = null;
//        String text = null;
        InputStream is = context.getResourceAsStream(realPath1);
        if (is != null) {
            try {
                isr = new InputStreamReader(is);
                
                br = new BufferedReader(isr);

                String line = null;
                
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split("=");

                    String stringKey = parts[0].trim();
                    String stringValue = parts[1].trim();

                    if (!stringKey.equals("") && !stringValue.equals("")) {
                        roadMap.put(stringKey, stringValue);
                    }
                }
                
            } catch (FileNotFoundException ex) {
                Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(MyContextListener.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                if (br != null) {
                    try {
                        br.close();
                    } catch (Exception e) {

                    }
                }
            }
        }

        context.setAttribute("MAP", roadMap);
        System.out.println("\n##################################################");
        System.out.println(roadMap.get("search"));
        System.out.println(realPath);
        System.out.println(realPath1);
        System.out.println("\n##################################################");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\n##################################################");

        System.out.println("contextDestroyed has been destroyed in " + this.getClass().getName());

        System.out.println("\n##################################################\n");
    }
}
