/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package models.basebuds;

import javax.persistence.Entity;


@Entity
public class PostalAddress extends ContactPoint {
    
    public String addressCountry;		//Text The country. the two-letter ISO 3166-1 alpha-2 country code.
    public String addressLocality;      //Text	The locality. For example, Mountain View.
    public String addressRegion;           //Text	The region. For example, CA.
    public String postOfficeBoxNumber;	//Text	The post offce box number for PO box addresses.
    public String postalCode;              //Text	The postal code. For example, 94043.
    public String streetAddress;           //Text	The street address. For example, 1600 Amphitheatre Pkwy.
}
