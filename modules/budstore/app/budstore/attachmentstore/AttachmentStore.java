/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budstore.attachmentstore;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import models.basebuds.BudEntity;
import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.pdf.PDFParser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;


/**
 *
 * @author Samuel Loup <samuel.loup at gmail.com>
 * 
 * 
 * Supported Document formats to implements
Package formats [a]	.tar
 	.jar
 	.zip
 	.bzip2
 	.gz
 	.tgz
Text Document Formats	.doc (MS Word Document)
 	.xls (MS Excel Document)
 	.ppt (MS PowerPoint Document)
 	.rtf (Rich Text Format)
 	.pdf (Adobe Portable Document Format)
 	.html
 	.xhtml
 	OpenDocument
 	.txt (Plain text)
Image Formats	.bmp
 	.gif
 	.png
 	.jpeg
 	.tiff
Audio Formats	.mp3
 	.aiff
 	.au
 	.midi
 	.wav
Misc Formats	.pst (Outlook mail)
 	.xml
 	.class (Java class files)
 */
public class AttachmentStore {
    
    private static final AttachmentStore INSTANCE = new AttachmentStore();
    
    public static AttachmentStore getInstance() {
        return INSTANCE;
    }
    
    
    public BudAttachment getBud(String identifier)
    {
        return BudAttachment.findById(identifier);
    }
    
    public void parseAndStorePDF(BudEntity b,File file)
    {
        InputStream input;
        try 
        {
            
            Tika tika = new Tika();
            System.out.println("Tika Detect: " + tika.detect(file));           
            input = new FileInputStream(file);
            ContentHandler textHandler = new BodyContentHandler();
            Metadata metadata = new Metadata();
            PDFParser parser = new PDFParser();        
            ParseContext ctx = new ParseContext();            
            parser.parse(input, textHandler, metadata, ctx);          
            input.close();
            for (String name : metadata.names()) 
            {
                    System.out.println("metadata: " + name + " - " + metadata.get(name));
            }
            //Saving attachment
            BudAttachment att = new BudAttachment(file,metadata.get("title"),metadata.get("Author"),"document/pdf");
            att.setId(b.identifier);         
            att.date = metadata.get("Creation-Date");
            att.type = metadata.get("Content-Type");
            att.creator = metadata.get("creator");
            att.publisher = metadata.get("producer");
            att.subject = metadata.get("subject");
            
            att.save();
            att.setAttachment(file);
            textHandler.toString();

        } catch (FileNotFoundException ex) {
            Logger.getLogger(AttachmentStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
                Logger.getLogger(AttachmentStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
                Logger.getLogger(AttachmentStore.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TikaException ex) {
                Logger.getLogger(AttachmentStore.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void storeAttachment(BudEntity b,File f)
    {
        if (b.haveAttachment) {
            System.out.println("Storing attachment...");
            if(f.getName().endsWith(".pdf"))
            {
                System.out.println("PDF detected!");
                parseAndStorePDF(b,f);
            }
            else
            {
                //simple attachment
                System.out.println("Unknow file, no meta-data extraction!");
                BudAttachment att = new BudAttachment(f,"unknow","unknow","unknow");
                att.setId(b.identifier);          
                att.save();
                att.setAttachment(f);
            }
        }
    }
    
}
