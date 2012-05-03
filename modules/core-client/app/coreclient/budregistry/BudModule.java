/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coreclient.budregistry;

import java.util.List;

/**
 *
 * @author sloup
 */
public interface BudModule 
{
    
    String getVersion();
    
    String getMetaData();
    
    List<String> getProvidedBuds();
    
    List<String> getDependenciesBuds();
    
}
