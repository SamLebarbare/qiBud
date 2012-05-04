
import play.jobs.Job;
import play.jobs.OnApplicationStop;

import budstore.graphstore.GraphStore;

@OnApplicationStop
public class BudStorageShutdown
        extends Job<Void>
{

    @Override
    public void doJob()
            throws Exception
    {
        GraphStore.shutdownNeo4jStore();
    }

}
