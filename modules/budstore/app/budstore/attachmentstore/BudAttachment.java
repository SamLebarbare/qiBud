/*
 * BudContainer based on DCMI MetaData Model
 */
package budstore.attachmentstore;

import play.modules.morphia.Model;
import java.util.Date;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Entity;
import java.io.File;
import play.modules.morphia.Blob;





@Entity
public class BudAttachment extends Model
{
    @Id
    public String identifier;	//URI
    public String title;	/** Titre du document : il s'agit a priori du titre principal du document. */
    public String creator;	//Créateur du document : nom de la personne, de l'organisation ou du service à l'origine de la rédaction du document.
    public String subject;	//Sujet et mots-clefs : mots-clefs, phrases de résumé, ou codes de classement. Il est préférable d'utiliser des mots-clefs choisis dans le cadre d'une politique de classement. Par exemple, on peut utiliser les codages de la bibliothèque du congrès (LCSH et LCC), le vocabulaire médical (MESH), ou les notations décimales des bibliothécaires (DDC et UDC).
    public String description;	//Description du document : résumé, table des matières, ou texte libre.
    public String publisher;	//Publicateur du document : nom de la personne, de l'organisation ou du service à l'origine de la publication du document.
    public String contributor;	//Contributeur au document : nom d'une personne, d'une organisation ou d'un service qui contribue ou a contribué à l'élaboration du document.
    public String date;         //Date d'un événement dans le cycle de vie du document : il peut s'agir par exemple de la date de création ou de la date de mise à disposition. Il est recommandé de spécifier la date au format W3CDTF (AAAA-MM-JJ).
    public String type;         //Type de bud
    public String format;	//Format du document : format physique ou électronique du document. Par exemple, type de média ou dimensions (taille, durée). On peut spécifier le matériel et le logiciel nécessaires pour accéder au document. Il est recommandé d'utiliser des termes clairement définis, par exemple les types MIME.
    public String source;	//URI
    public String language;	//Langue du document : il est recommandé d'utiliser un code de langue conforme au format RFC4646.
    public String relation;	//Lien vers une ressource liée : il est recommandé d'utiliser une dénomination formelle des ressources, par exemple leur URI.
    public String coverage;	//Portée du document : la portée inclut un domaine géographique, un laps de temps, ou une juridiction (nom d'une entité administrative). Il est recommandé d'utiliser des représentations normalisées de ces types de données, par exemple TGN (Thesaurus of Geographic Names, un dictionnaire de noms de lieux), ISO3166, Point ou Box pour la portée spatiale, Period ou W3CDTF pour la portée temporelle.
    public String rights;	//Droits relatifs à la ressource : permet de donner des informations sur le statut des droits du document, par exemple la présence d'un copyright, ou un lien vers le détenteur des droits. L'absence de cette propriété ne présume pas que le document est libre de droits.
    
    
    public Blob attachment;
    
    public BudAttachment(File file,String title,String author,String type)
    {
        this.date = new Date().toString();
        this.title = title;
        this.creator = author;
        this.type = type;
    }
    
    
    public void setAttachment(File file) 
    {
        this.attachment = new Blob(file, this.type);
        save();
    }
    
    @Override
    public Object getId() 
    {
        return identifier;
    }
    
    @Override
    protected void setId_(Object id) 
    {
        this.identifier = processId_(id);
    }
    
    protected static String processId_(Object id) {
        return id.toString();
    }
    
    
    
}
