package budstore;

import java.util.ArrayList;
import java.util.List;

import budstore.attachmentstore.AttachmentStore;
import budstore.attachmentstore.BudAttachment;
import budstore.graphstore.BudNode;
import budstore.graphstore.GraphStore;
import models.basebuds.BudEntity;

/**
 * @author Samuel Loup <samuel.loup at gmail.com>
 */
public class Bud
{

    public static List<Bud> findAll()
    {
        List<Bud> buds = new ArrayList<Bud>();
        for ( BudEntity budEntity : BudEntity.<BudEntity>findAll() ) {
            Bud bud = new Bud( budEntity.identifier );
            bud.entity = budEntity;
            buds.add( bud );
        }
        return buds;
    }

    public final String identity;

    private BudEntity entity;

    private BudNode graphnode;

    private BudAttachment attachment;

    public Bud( String identity )
    {
        this.identity = identity;
    }

    public BudEntity entity()
    {
        if ( entity == null ) {
            entity = BudEntity.findById( this.identity );
        }
        return entity;
    }

    public BudNode graphNode()
    {
        if ( graphnode == null ) {
            graphnode = GraphStore.getBud( identity );
        }
        return graphnode;
    }

    public BudAttachment attachments()
    {
        if ( attachment == null ) {
            attachment = AttachmentStore.getInstance().getBud( identity );
        }
        return attachment;
    }

}
