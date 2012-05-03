
package budstore;

import budstore.attachmentstore.AttachmentStore;
import budstore.attachmentstore.BudAttachment;
import budstore.graphstore.GraphStore;
import java.io.File;
import java.util.List;
import java.util.UUID;
import models.basebuds.Bud;
import play.Play;

/**
 *
 * @author Samuel Loup <samuel.loup at gmail.com>
 * Perform Cross Storage Operations
 * 
 */
public class BudStore {
    
    private static final BudStore INSTANCE = new BudStore();
    
    public static BudStore getInstance() {
        return INSTANCE;
    }
    
    
    /* URI */
    public String setIdentifier(String type)
    {
        
        /*
         *   foo://username:password@example.com:8042/over/there/index.dtb?type=animal;name=ferret#nose
          \ /   \________________/\_________/ \__/            \___/ \_/ \_____________________/ \__/
           |           |               |       |                |    |           |                |
           |       utilisateur        hôte    port              |    |        requête          fragment
           |    \_______________________________/ \_____________|____|/
        schéma                  |                         |     |    |
           |                 domaine                    chemin  |    |
           |                                                    |    |
           |            chemin                     peut être interprété comme un nom de fichier
           |   ___________|____________                              |
          / \ /                        \                             |
          urn:example:animal:ferrett:nose            peut être interprété comme une extension
         */
        return "http://"+ Play.configuration.get("http.address") + ":" + Play.configuration.get("http.port") + Play.configuration.get("http.path")+type+"/entity/"+UUID.randomUUID().toString();
        
    }
    public String getIdentifierFromUID(String type,String uid)
    {
        return "http://"+ Play.configuration.get("http.address") + ":" + Play.configuration.get("http.port") + Play.configuration.get("http.path")+type+"/entity/"+uid;
    }
    
    
    public Bud extract(Bud b)
    {
        return null;
    }
    public void store(Bud b,File f)
    {
        //First save in the graphDB
        GraphStore.storeABud(b);
        //Store the attachment
        if(b.haveAttachment)
        {
            AttachmentStore.getInstance().storeAttachment(b, f);
        }
    }
    public void archive(Bud b)
    {
        //TODO
    }
    
    public void delete(Bud b)
    {
        GraphStore.deleteABud(b.identifier);
        if(b.haveAttachment)
        {
            AttachmentStore.getInstance().getBud(b.identifier).delete();
        }
        b.delete();
        
    }
    
    
    //MAINTENANCE OP
    
    public void remapAttachments()
    {
        System.out.println("!remap attachment!");
        List<BudAttachment> bas = BudAttachment.findAll();
        for(BudAttachment ba : bas)
        {
            Bud bud = Bud.findById(ba.identifier);
            if(bud!=null)
            {
                bud.haveAttachment = true;
                bud.save();
            }
            else
            {
                System.out.println("Orphan found! delete it: " + ba.title);
                ba.delete();
                
            }
            
        }
    }
    
    
    
}
