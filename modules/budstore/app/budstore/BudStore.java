
package budstore;

import budstore.attachmentstore.AttachmentStore;
import budstore.attachmentstore.BudAttachment;
import budstore.graphstore.GraphStore;
import java.io.File;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;
import models.basebuds.BudEntity;
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
    
    private final String IDENTITY_JVM_PREFIX = UUID.randomUUID().toString()+"-";
    private final AtomicLong IDENTITY_JVM_COUNT = new AtomicLong( 0);
    
    public String newBudIdentity(String type)
    {
        return IDENTITY_JVM_PREFIX + IDENTITY_JVM_COUNT.getAndIncrement();
    }
    
    public BudEntity extract(BudEntity b)
    {
        return null;
    }
    public void store(BudEntity b,File f)
    {
        // JPA explicit save
        b.save();
        //First save in the graphDB
        GraphStore.storeABud(b);
        //Store the attachment
        AttachmentStore.getInstance().storeAttachment(b, f);
    }
    public void archive(BudEntity b)
    {
        //TODO
    }
    
    public void delete(BudEntity b)
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
            BudEntity bud = BudEntity.findById(ba.identifier);
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
