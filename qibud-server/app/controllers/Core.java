package controllers;

import coreclient.budregistry.BudModule;
import coreclient.budregistry.BudRegistry;
import java.util.List;
import play.mvc.*;


public class Core extends Controller {
    

    public static void index() 
    {       
        render();
    }
    
    public static void packages()
    {
        BudRegistry budReg = BudRegistry.getInstance();
        List<String> modules = budReg.listAllBudModules();
        render(modules);
    }
    public static void moduleDetails(String moduleName)
    {
        System.out.println("Details on " + moduleName);
        BudRegistry budReg = BudRegistry.getInstance();
        BudModule budModule = budReg.getBudModule(moduleName);
        System.out.println(budModule.getVersion()); 
        index();
    }
    

}