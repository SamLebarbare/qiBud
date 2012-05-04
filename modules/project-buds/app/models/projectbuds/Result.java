/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.projectbuds;


import javax.persistence.*;
import models.basebuds.BudEntity;
import models.basebuds.Person;

@Entity
public class Result extends BudEntity {


    @ManyToOne
    public Mission mission;
    
    @ManyToOne
    public Person actor;
    public boolean assigned;
    public boolean isASuccess=false;
    public boolean isAFail=false;
    public boolean define=false;
    public String status;
    

    public Result(String source)
    {
       
    }

   
}
