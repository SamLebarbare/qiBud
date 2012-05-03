/*
 * ContactPoint Data Model
 */
package models.basebuds;


import javax.persistence.Entity;


@Entity
public class ContactPoint extends Bud {
    
   
    
    public String contactType;	 //Text	A person or organization can have different contact points, for different purposes. For example, a sales contact point, a PR contact point and so on. This property is used to specify the kind of contact point.
    public String email;             //Text	Email address.
    public String faxNumber;	 //Text	The fax number.
    public String telephone;	 //Text	The telephone number.
    
}
