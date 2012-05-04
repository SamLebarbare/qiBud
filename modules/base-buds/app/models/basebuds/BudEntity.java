/*
 * Bud DataModel
 * JPA + Héritage = http://www.chicoree.fr/w/JPA_et_l'h%C3%A9ritage
 */
package models.basebuds;




import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import play.db.jpa.GenericModel;

@Entity
@Inheritance(strategy=InheritanceType.TABLE_PER_CLASS)
public class BudEntity extends GenericModel {

    @Id
    public String identifier;
    public String type;
    public String title;
    public Date postedAt;
    public String content;
    public boolean haveAttachment;
    
    public void Bud()
    {
        this.type = "BUD";
    }
    
    

}
