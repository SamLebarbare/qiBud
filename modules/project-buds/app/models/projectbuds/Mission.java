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
public class Mission extends Bud {
    
    @OneToMany(mappedBy="mission")
    public List<Result> results;
    @OneToMany(mappedBy="mission")
    public List<Action> actions;

    @ManyToOne
    public Project project;

    public String status;
    
    @ManyToOne
    public Person actor;
    public boolean assigned;

    public Mission(String source)
    {

    }

    

    

    
}
