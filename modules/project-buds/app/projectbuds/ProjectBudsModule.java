package projectbuds;


import coreclient.budregistry.BudModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Sam Le Barbare
 */
public class ProjectBudsModule implements BudModule{

    public String getVersion() {
        return "Project buds 1.0";
    }

    public String getMetaData() {
        return "";
    }

    public List<String> getProvidedBuds() {
        
        List<String> buds = new ArrayList<String>();
        buds.add("Action");
        buds.add("Bug");
        buds.add("Choice");
        buds.add("Idea");
        buds.add("Mission");
        buds.add("Project");
        buds.add("Result");
        return buds;
    }

    public List<String> getDependenciesBuds() {
        return Collections.emptyList();
    }
    
}
