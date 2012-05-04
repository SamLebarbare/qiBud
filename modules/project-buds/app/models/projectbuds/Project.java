/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.projectbuds;

import java.util.List;

import javax.persistence.*;
import models.basebuds.BudEntity;
import models.basebuds.Person;

@Entity
public class Project extends BudEntity {
    
    @OneToMany(mappedBy="project")
    public List<Mission> missions;


    public String status;
    
    @ManyToOne
    public Person actor;
    public boolean assigned;


    public Project(String source)
    {
    }


    

}
