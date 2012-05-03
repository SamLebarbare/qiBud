package projectbuds;


import coreclient.budregistry.BudRegistry;
import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job<Void>
{
    

    @Override
    public void doJob() {
        
        System.out.println("START: Project-Buds Package initializazion...");
        //Enregistrement du module
        System.out.println("PROJECT-BUDS: Auto-Installing Package...");
        BudRegistry budReg = BudRegistry.getInstance();
        budReg.installBud("ProjectBuds", ProjectBudsModule.class);
        System.out.println("Auto-Installing Package [DONE]");
        
        
    }

    @Override
    public void _finally() 
    {
        System.out.println("Project-Buds Package initializazion job [DONE]");
    }
    
    



}
