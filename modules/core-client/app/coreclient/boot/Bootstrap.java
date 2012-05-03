package coreclient.boot;




import coreclient.budregistry.BudRegistry;
import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job<Void>
{
    

    @Override
    public void doJob() 
    {
        System.out.println("////////////CORE CLIENT/////////////");
        System.out.println("START: Core Client initializazion...");
    }
    
    



}
