//import eventstore.client.eventhandlers.EventHandlerRegistry;
//import handlers.DefaultHandler;
import budstore.graphstore.GraphStore;
import play.jobs.*;

@OnApplicationStart
public class Bootstrap extends Job<Void>
{
    

    @Override
    public void doJob() {
        
        System.out.println("START: Bud Storage initialization...");
        GraphStore.initNeo4jStore();     
        System.out.println("Bud Storage initialization [DONE]");
        
        
    }

    @Override
    public void _finally() 
    {
        
    }
    
    



}
