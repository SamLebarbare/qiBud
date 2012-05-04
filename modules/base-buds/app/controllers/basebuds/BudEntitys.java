/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers.basebuds;

import budstore.BudStore;
import java.io.File;
import budstore.Bud;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import models.basebuds.BudEntity;
import play.data.validation.Required;
import play.data.validation.Validation;

/**
 *
 * @author Samuel Loup aka Sam Le Barbare
 */
public class BudEntitys extends BaseBuds {

    public static void index()
    {   
        List<BudEntity> buds = BudEntity.findAll();
        render(buds);
    }
    
    public static void add() 
    {
        render();
    }
    
    public static void showFromUID(@Required String uid)
    {
        show(uid);
    }
    
    public static void show(@Required String uri)
    {
        if (validation.hasErrors()) {
            flash.error("show() without URI");
            params.flash();
            index();
        }
        Bud b = new Bud(uri);
        render(b);
    }
    
    public static void getAttachment(@Required String uri)
    {
        if (validation.hasErrors()) {
            flash.error("getAttachment() without URI");
            params.flash();
            index();
        }
        Bud bud = new Bud(uri);
        InputStream attachment = bud.attachment.attachment.get();
        
        renderBinary(attachment);
    }
    
    public static void delete(String uri)
    {
        BudEntity b = BudEntity.findById(uri);
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

        BudEntity newBud = new BudEntity();
        newBud.type = "buds";
        newBud.title = budTitle;
        newBud.content = content;
        newBud.identifier = BudStore.getInstance().newBudIdentity(newBud.type);
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
