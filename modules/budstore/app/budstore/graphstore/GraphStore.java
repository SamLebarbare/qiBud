/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package budstore.graphstore;

import java.util.ArrayList;
import java.util.List;
import models.basebuds.Bud;
import org.neo4j.graphdb.Direction;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.index.Index;
import org.neo4j.graphdb.index.IndexHits;
import org.neo4j.kernel.EmbeddedGraphDatabase;
import play.Play;

/**
 * @author Samuel Loup aka Sam Le Barbare
 */
public class GraphStore {

    private static EmbeddedGraphDatabase INSTANCE = new EmbeddedGraphDatabase(Play.configuration.get("budstorage.path").toString());
    private static Index<Node> nodeIndex;
    
    
    //BUD REFERENCE NODE
    public static Node budsReferenceNode;

    private static enum RelTypes implements RelationshipType {

        BUD,
        BUDS_REFERENCE
    }

    public static EmbeddedGraphDatabase getDb() {
        return INSTANCE;
    }

    public static void initNeo4jStore() 
    {
        System.out.println("START: Initializing Neo4j Bud Storage...");
        nodeIndex = INSTANCE.index().forNodes("nodes");
        registerShutdownHook(INSTANCE);
        System.out.println("BUDSTORE: Register Shutdown Hook [OK]");
        initializeRefNodes();  
        System.out.println("BUDSTORE: Checking Bud Store Size...");
        int size = getAllBuds().size();
        System.out.println("BUDSTORE: " + size + " buds");
        System.out.println("Initializing Neo4j Bud Storage [DONE]");
    }

    
    private static void initializeRefNodes()
    {
        
        Transaction tx = INSTANCE.beginTx();
        Node refNode = INSTANCE.getReferenceNode();
        if(refNode==null)
        {
            try 
            {
                budsReferenceNode = INSTANCE.createNode();
                INSTANCE.getReferenceNode().createRelationshipTo(budsReferenceNode, RelTypes.BUDS_REFERENCE);           
                tx.success();
            } 
            finally 
            {
                System.out.println("BUDSTORE: Bud Reference Node [OK]");
                tx.finish();
            }
        }
        else
        {
            try 
            {
                budsReferenceNode = INSTANCE.getReferenceNode();     
                tx.success();
            
            } 
            finally 
            {
                System.out.println("BUDSTORE: Bud Reference Node Already Exist! [OK]");
                tx.finish();
            }
            
        }
        
    }
    private static void registerShutdownHook(final GraphDatabaseService graphDb) 
    {
        Runtime.getRuntime().addShutdownHook(new Thread() 
        {
            @Override
            public void run() {
                graphDb.shutdown();
                System.out.println("BUDSTORE: Shutdown Hook [OK]");
            }
        });
    }

    ////////////BUD STORAGE
    //
    public static void storeABud(Bud aBud) 
    {
        
        System.out.println("BUDSTORE: New TX");
        Transaction tx = INSTANCE.beginTx();
        try {
            
            Node budNode = INSTANCE.createNode();
            budsReferenceNode.createRelationshipTo( budNode, RelTypes.BUD );
            budNode.setProperty("type", aBud.type.toString());
            budNode.setProperty("date", aBud.postedAt.toString());
            budNode.setProperty("title", aBud.title);
            budNode.setProperty("identifier", aBud.identifier);
            nodeIndex.add(budNode,"identifier", aBud.identifier);
            tx.success();
            System.out.println("BUDSTORE: " + budNode.getProperty("identifier").toString());
            
        } finally {
            tx.finish();
            System.out.println("BUDSTORE: Finish TX");
        }
        
    }
    
    
    public static void deleteABud(String identifier)
    {
       Transaction tx = INSTANCE.beginTx();
        try 
        {
            IndexHits<Node> hits = nodeIndex.get( "identifier", identifier );
            
            Node budNode = hits.getSingle();
            System.out.println("DELETE: " + budNode.getId());
            budNode.delete();
        } 
        finally 
        {
            tx.finish();
            
        } 
        
    }
    
    //Bud Getters
    
    public static BudNode getBud(String identifier)
    {
       Transaction tx = INSTANCE.beginTx();
        try 
        {
            IndexHits<Node> hits = nodeIndex.get( "identifier", identifier );
            Node budNode = hits.getSingle();
            BudNode bud = new BudNode();
            System.out.println("GET:"+ identifier);
            bud.identifier = budNode.getProperty("identifier").toString();
            return bud;
        } 
        finally 
        {
            tx.finish();
            
        } 
        
    }
    public static List<BudNode> getAllBuds()
    {
        List<BudNode> buds = new ArrayList<BudNode>();
        Transaction tx = INSTANCE.beginTx();
        try 
        {
            for ( Relationship relationship : budsReferenceNode.getRelationships( RelTypes.BUD, Direction.OUTGOING ) )
            {
                Node budNode = relationship.getEndNode();
                BudNode bud = new BudNode();
                bud.identifier = budNode.getProperty("identifier").toString();
                buds.add(bud);
            }
        } 
        finally 
        {
            tx.finish();
            
        }
        
        return buds;
    }
    
}
    
   
