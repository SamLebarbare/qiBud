/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.projectbuds;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import models.basebuds.Bud;
import models.basebuds.Person;

@Entity
public class Choice extends Bud {

    public String source; //URI

    @ManyToOne
    public Mission mission;
    
    @ManyToMany
    public List<Person> mustChoice = new ArrayList<Person>();

    public boolean choice=false;
    public boolean isChoose=false;
    

    public Choice(String source)
    {
        
    }
    
   
    
    

}
