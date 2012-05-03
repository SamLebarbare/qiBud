package basebuds;


import coreclient.budregistry.BudModule;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 *
 * @author Sam Le Barbare
 */
public class BaseBudsModule implements BudModule{

    public String getVersion() {
        return "Base buds 1.0";
    }

    public String getMetaData() {
        return "";
    }

    public List<String> getProvidedBuds() {
        
        List<String> buds = new ArrayList<String>();
        buds.add("Persons");
        buds.add("Organization");
        buds.add("ContactPoint");
        buds.add("PostalAddress");
        return buds;
    }

    public List<String> getDependenciesBuds() {
        return Collections.emptyList();
    }
    
}
