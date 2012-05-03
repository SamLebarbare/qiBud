/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.basebuds;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;


@Entity
public class Organization extends Bud {
 
public PostalAddress address;	//PostalAddress	Physical address of the item.

@OneToMany
public List<ContactPoint> contactPoints;	//ContactPoint	A contact point for a person or organization.
public String email;	 //Text	Email address.

@OneToMany
public List<Person> employees;	//Person	People working for this organization.
//events	Event	Upcoming or past events associated with this place or organization.
public String faxNumber;	 //Text	The fax number.

@OneToMany
public List<Person> founders;	//Person	A person who founded this organization.
public Date foundingDate;	 //Date	The date that this organization was founded.
public String interactionCount;	 //Text	A count of a specific user interactions with this item—for example, 20 UserLikes, 5 UserComments, or 300 UserDownloads. The user interaction type should be one of the sub types of UserInteraction.
public PostalAddress location;	//Place or PostalAddress	The location of the event or organization.

@OneToMany
public List<Person> members;	//Person or Organization	A member of this organization.
//reviews	Review	Review of the item.
public String telephone;	 //Text	The telephone number.
    
}
