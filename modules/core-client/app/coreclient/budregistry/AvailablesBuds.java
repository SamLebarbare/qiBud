/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package coreclient.budregistry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import play.vfs.VirtualFile;

/**
 *
 * @author Sam Le Barbare
 * Little class for returning buds models class files from buds modules
 */
public class AvailablesBuds {
    
    
    public List<String> get()
    {
        
        List<String> availableBuds = new ArrayList<String>();
        //Get running modules VirtualFiles
        Collection<VirtualFile> vfs = play.Play.modules.values();
        
        
        for(VirtualFile f : vfs)
        {
            //Search for a "bud" modules directory
            if(f.getName().contains("bud") && f.isDirectory())
            {
                    //Getting models buds java class
                    VirtualFile jclass = f.child("app").child("models").list().get(0);
                        
                    for(VirtualFile jc : jclass.list())
                    {
                        availableBuds.add(jc.getName());
                    }
                
            }
            
        }
        return availableBuds;
    }
            
    
}
