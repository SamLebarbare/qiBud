
import play.jobs.Job;
import play.jobs.OnApplicationStop;

import budstore.graphstore.GraphStore;

@OnApplicationStop
public class Shutdown
        extends Job<Void>
{

    @Override
    public void doJob()
            throws Exception
    {
        GraphStore.shudownNeo4jStore();
    }

}
