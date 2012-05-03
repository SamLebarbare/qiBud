/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.basebuds;

import budstore.BudStore;
import java.io.File;
import budstore.BudStoreEntity;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import models.basebuds.Bud;
import play.data.validation.Required;
import play.data.validation.Validation;

/**
 *
 * @author Samuel Loup aka Sam Le Barbare
 */
public class Buds extends BaseBuds {

    public static void index()
    {   
        List<Bud> buds = Bud.findAll();
        render(buds);
    }
    
    public static void add() 
    {
        render();
    }
    
    public static void showFromUID(@Required String uid)
    {
        show(BudStore.getInstance().getIdentifierFromUID("buds",uid));
    }
    
    public static void show(@Required String uri)
    {
        if (validation.hasErrors()) {
            flash.error("show() without URI");
            params.flash();
            index();
        }
        BudStoreEntity b = new BudStoreEntity(uri);
        render(b);
    }
    
    public static void getAttachment(@Required String uri)
    {
        if (validation.hasErrors()) {
            flash.error("getAttachment() without URI");
            params.flash();
            index();
        }
        BudStoreEntity bud = new BudStoreEntity(uri);
        InputStream attachment = bud.attachment.attachment.get();
        
        renderBinary(attachment);
    }
    
    public static void delete(String uri)
    {
        Bud b = Bud.findById(uri);
        BudStore.getInstance().delete(b);
        index();
    }
    
    public static void create(@Required String budTitle,@Required String content,File file) 
    {
        if (Validation.hasErrors()) {
            params.flash();
            Validation.keep();
            add();
        }

        Bud newBud = new Bud();
        newBud.type = "buds";
        newBud.title = budTitle;
        newBud.content = content;
        newBud.identifier = BudStore.getInstance().setIdentifier(newBud.type);
        newBud.postedAt = new Date();
        if(file!=null)
        {
            newBud.haveAttachment = true;
        }
        newBud.save();
        
        
        BudStore.getInstance().store(newBud, file);
        
        show(newBud.identifier);
    }
}
