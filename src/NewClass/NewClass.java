/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewClass;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XQueryService;

public class NewClass {
    private static String q1 = "for $c in fn:doc('Kps_manager.xml')/Business_events/mail_events/mail\n" + "return\n" + "<a>{$c/event/text()},{$c/event_time/text()},{$c/weight/text()},{$c/volume/text()},{$c/time/text()},{$c/priority_id/text()},{$c/origin/text()},{$c/destination/text()},{$c/price/text()},{$c/cost/text()}</a>";
    private  String q2 = "";
    private  String q3 = "";
    private  String q4 = "";           
    private  String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private  String driver = "org.exist.xmldb.DatabaseImpl";
    
    public String[] aa(String Query) throws Exception{              
        Class cl = Class.forName(driver);
        String[] Value2 = null;
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        Collection col=DatabaseManager.getCollection(URI+"/db/kps");
    XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
    service.setProperty("indent","yes");
    ResourceSet result =service.query(Query);
    ResourceIterator i =result.getIterator();
    while(i.hasMoreResources()){
        Resource r=i.nextResource();
        String Value=(String)r.getContent();
        Value2 = Value.substring(3, (Value.length()-4)).split(","); 
    }
        return Value2;
    }


public static void main(String[] args) throws Exception {}


}