/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budstore;

import budstore.attachmentstore.AttachmentStore;
import budstore.attachmentstore.BudAttachment;
import budstore.graphstore.BudNode;
import budstore.graphstore.GraphStore;
import models.basebuds.BudEntity;

/**
 *
 * @author Samuel Loup <samuel.loup at gmail.com>
 */
public class Bud {
    
    public String uri;
    public BudEntity entity;
    public BudNode graphdata;
    public BudAttachment attachment; 
    
    
    public Bud(String identifierURI)
    {
        this.uri = identifierURI;
        this.entity = BudEntity.findById(this.uri);
        this.graphdata = GraphStore.getBud(this.uri);
        this.attachment = AttachmentStore.getInstance().getBud(this.uri);
    }
}
