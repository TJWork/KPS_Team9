/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package datamanagement;




import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;

import org.xmldb.api.modules.XQueryService;


/**
 *
 * @author kuangwentao
 */
public class company {
    private static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    public static void main(String[] args) throws Exception {
    final String driver = "org.exist.xmldb.DatabaseImpl";

        Class cl = Class.forName(driver);
        Database database = (Database) cl.newInstance();
//        database.setProperty("create-database", "true");
        DatabaseManager.registerDatabase(database);
        Collection col=DatabaseManager.getCollection(URI+"/db/kps");
        
    String xquery= "for $c in fn:doc('Kps_client.xml')/Kps/mails/mail return $c";    
    XQueryService service = (XQueryService) col.getService("XQueryService","1.0");
    service.setProperty("indent","yes");
    ResourceSet result =service.query(xquery);
    ResourceIterator i =result.getIterator();
//    while(i.hasMoreResources()){
//        Resource r=i.nextResource();
//        String Value=(String)r.getContent();
//        int mailindex = Value.indexOf("<id='>");
//        int endmailindex =Value ;
//        System.out.println(Value);
//    }    
     
    }    

}
