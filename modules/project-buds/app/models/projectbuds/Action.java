/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.projectbuds;

/**
 *
 * @author sloup
 */
import javax.persistence.*;
import models.basebuds.BudEntity;
import models.basebuds.Person;

@Entity
public class Action extends BudEntity {



    @ManyToOne
    public Mission mission;
    
    @ManyToOne
    public Bug bug;

    public String status;
   

    @ManyToOne
    public Person actor;
    public boolean assigned;


    public Action(String source)
    {
        
    }


    

}
