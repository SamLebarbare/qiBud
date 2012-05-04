
import play.jobs.Job;
import play.jobs.OnApplicationStart;

import budstore.graphstore.GraphStore;

@OnApplicationStart
public class Bootstrap
        extends Job<Void>
{

    @Override
    public void doJob()
    {

        System.out.println( "START: Bud Storage initialization..." );
        GraphStore.initNeo4jStore();
        System.out.println( "Bud Storage initialization [DONE]" );


    }

}
