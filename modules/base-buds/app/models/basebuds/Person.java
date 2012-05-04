/*
 * Person DataModel
 */
package models.basebuds;


import java.util.Date;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.OneToMany;

@Entity
public class Person extends BudEntity {
    
    public String additionalName;	 //Text	An additional name for a Person, can be used for a middle name.
    public PostalAddress address;	//PostalAddress	Physical address of the item.
    public Organization affiliation;	//Organization	An organization that this person is affiliated with. For example, a school/university, a club, or a team.

    public String awards;	 //Text	Awards won by this person or for this creative work.
    public Date birthDate;	// Date	Date of birth.
    public Person children;	//Person	A child of the person.
    
    @OneToMany
    public List<Person> colleagues;	//Person	A colleague of the person.
    
    @OneToMany
    public List<ContactPoint> contactPoints;	//ContactPoint	A contact point for a person or organization.
    public Date deathDate;	 //Date	Date of death.
    public String email;	 //Text	//Email address.
    public String familyName;	 //Text	Family name. In the U.S., the last name of an Person. This can be used along with givenName instead of the Name property.
    public String faxNumber;	 //Text	The fax number.
    
    @OneToMany
    public List<Person> follows;	//Person	The most generic uni-directional social relation.
    public String gender;	 //Text	Gender of the person.
    public String givenName;	 //Text	Given name. In the U.S., the first name of a Person. This can be used along with familyName instead of the Name property.
    public ContactPoint homeLocation;	//Place or ContactPoint	A contact location for a person's residence.
    public String honorificPrefix;	 //Text	An honorific prefix preceding a Person's name such as Dr/Mrs/Mr.
    public String honorificSuffix;	 //Text	An honorific suffix preceding a Person's name such as M.D. /PhD/MSCSW.
    public String interactionCount;	 //Text	A count of a specific user interactions with this item—for example, 20 UserLikes, 5 UserComments, or 300 UserDownloads. The user interaction type should be one of the sub types of UserInteraction.
    public String jobTitle;	 //Text	The job title of the person (for example, Financial Manager).
    
    @OneToMany
    public List<Person> knows;	//Person	The most generic bi-directional social/work relation.
    
    @OneToMany
    public List<Organization> memberOf;	//Organization	An organization to which the person belongs.
    
    public String nationality;	//Country	Nationality of the person.
    
    @OneToMany
    public List<Person> parents;	//Person	A parents of the person.
//performerIn	//Event	Event that this person is a performer or participant in.
    
    @OneToMany
    public List<Person> relatedTo;	//Person	The most generic familial relation.
    
    @OneToMany
    public List<Person> siblings;	//Person	A sibling of the person.
    public Person spouse;	//Person	The person's spouse.
    public String telephone;	 //Text	The telephone number.
    public ContactPoint workLocation;	//Place or ContactPoint	A contact location for a person's place of work.
    
    @OneToMany
    public List<Organization> worksFor;	//Organization	Organizations that the person works for.

}
