/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coreclient.budregistry;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.NullArgumentException;
import org.apache.commons.lang.StringUtils;
import play.classloading.ApplicationClasses.ApplicationClass;
import play.db.jpa.Model;

/**
 *
 * @author sloup
 */
public class BudRegistry{
    
    
    private static final BudRegistry INSTANCE = new BudRegistry();
    
    public static BudRegistry getInstance() {
        return INSTANCE;
    }
    
    private final List<BudRegisterEntry> registry = new ArrayList<BudRegisterEntry>();
    
    private BudRegistry() {
        
        
    }
    
    
    
    
    public void installBud(String name, Class <? extends BudModule> budClass) 
    {
        
        if (name == null) {
            throw new NullArgumentException("name");
        }
        if (budClass == null) {
            throw new NullArgumentException("budClass");
        }
        try {
            
            budClass.newInstance();
            registry.add(new BudRegisterEntry(name,budClass));
            System.out.println("Installing "+ budClass.toString() + " [DONE]");
        } catch (InstantiationException ex) {
            throw new IllegalArgumentException("Provided BudRegisterEntry cannot be instantiated, it needs a no-args constructor.", ex);
        } catch (IllegalAccessException ex) {
            throw new IllegalArgumentException("Provided BudRegisterEntry cannot be instantiated, it needs a no-args constructor.", ex);
        }
    }
    
    
    public List<String> listAllBudModules()
    {
        
        List<String> budEntries = new ArrayList<String>();
        for (BudRegisterEntry entry : registry) 
        {
 
            budEntries.add(entry.name);
                
        }
        
        return budEntries;
    }
    
    public BudModule getBudModule(String name)
    {
        if (StringUtils.isEmpty(name)) {
            return null;
        }
        BudModule budEntry = null;
        for (BudRegisterEntry entry : registry) 
        {
            
            if(name.equals(entry.name))
            {
                
                try {
                    budEntry = (BudModule) entry.budClass.newInstance();
                } catch (InstantiationException ex) {
                    Logger.getLogger(BudRegistry.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(BudRegistry.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
                
        }
        
        return budEntry;
    }
    
    private static class BudRegisterEntry {

        private final String name;
        private final Class <? extends BudModule> budClass;

        public BudRegisterEntry(String name, Class <? extends BudModule> budClass) {
            this.name = name;
            this.budClass = budClass;
        }
    }
}
