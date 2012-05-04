/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package models.projectbuds;


import javax.persistence.*;
import models.basebuds.BudEntity;

@Entity
public class Idea extends BudEntity {

    public String source; //URI

    @ManyToOne
    public Project project;

    @ManyToOne
    public Mission mission;

    public Idea(String source)
    {
    }

}
