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
import models.basebuds.Person;
import play.data.validation.Required;
import play.data.validation.Validation;

/**
 *
 * @author Samuel Loup aka Sam Le Barbare
 */
public class Persons extends BaseBuds{
    
    public static void index()
    {                  
        List<Person> buds = Person.findAll();
        render(buds);
    }
    
    public static void add() {
        render();
    }
    
    public static void showFromUID(@Required String uid)
    {
        show(uid);
    }
    
    
    public static void show(String uri)
    {
        Bud b = new Bud(uri);
        render(b);
    }
    
    public static void getAttachment(String uri)
    {
        
        Bud bud = new Bud(uri);
        InputStream attachment = bud.attachment.attachment.get();
        
        renderBinary(attachment);
    }

    public static void delete(String uri)
    {
        Person b = Person.findById(uri);
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
        
        Person newBud = new Person();
        newBud.type = "persons";
        newBud.title = budTitle;
        newBud.content = content;
        newBud.identifier = BudStore.getInstance().newBudIdentity(newBud.type);
        newBud.postedAt = new Date();
        newBud.save();
        
        
        BudStore.getInstance().store(newBud, file);
        
        show(newBud.identifier);
        
        

    }
}
