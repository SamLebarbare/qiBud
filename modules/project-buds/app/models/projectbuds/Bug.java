/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.projectbuds;


import java.util.List;
import javax.persistence.*;
import models.basebuds.Bud;
import models.basebuds.Person;


@Entity
public class Bug extends Bud {

    @ManyToOne
    public Project project;

    @ManyToOne
    public Mission mission;
    
    @OneToMany(mappedBy="bug")
    public List<Action> actions;
    
    public String status;
    @ManyToOne
    public Person actor;
    public boolean assigned;

    public Bug(String source)
    {

    }
    
  
    
   

}
