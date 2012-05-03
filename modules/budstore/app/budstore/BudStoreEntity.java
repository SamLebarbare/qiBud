/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budstore;

import budstore.attachmentstore.AttachmentStore;
import budstore.attachmentstore.BudAttachment;
import budstore.graphstore.BudNode;
import budstore.graphstore.GraphStore;
import models.basebuds.Bud;

/**
 *
 * @author Samuel Loup <samuel.loup at gmail.com>
 */
public class BudStoreEntity {
    
    public String uri;
    public Bud entity;
    public BudNode graphdata;
    public BudAttachment attachment; 
    
    
    public BudStoreEntity(String identifierURI)
    {
        this.uri = identifierURI;
        this.entity = Bud.findById(this.uri);
        this.graphdata = GraphStore.getBud(this.uri);
        this.attachment = AttachmentStore.getInstance().getBud(this.uri);
    }
}
