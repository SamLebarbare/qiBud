package basebuds;


import coreclient.budregistry.BudRegistry;
import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job<Void>
{
    

    @Override
    public void doJob() {
        
        System.out.println("////////////BASE BUDS PACKAGE////////////");
        System.out.println("START: Base-Bud Package initializazion...");
        //Enregistrement du module
        System.out.println("BASE-BUDS: Auto-Installing Package...");
        BudRegistry budReg = BudRegistry.getInstance();
        budReg.installBud("BaseBuds", BaseBudsModule.class);
        System.out.println("Auto-Installing Package [DONE]");
        
        
    }

    
    
    



}
