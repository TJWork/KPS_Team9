package NewClass;

import domain.DiscontinueEvent;
import domain.MailEvent;
import domain.UpdateCustomerPriceEvent;
import domain.UpdateTransportPriceEvent;
import java.util.ArrayList;
import java.util.List;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.Resource;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.modules.XQueryService;

public class Insert {

    
    private final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private final static String driver = "org.exist.xmldb.DatabaseImpl";

    public void bb(String query) throws Exception {
        System.out.println("reading query: " + query);
        Class cl = Class.forName(driver);
        String[] Value2 = null;
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        Collection col = DatabaseManager.getCollection(URI + "/db/kps");
        XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
        service.setProperty("indent", "yes");
        service.query(query);
    }
    
    public void addmail(MailEvent mailEvent) throws Exception{    
        String xquery= "update insert\n<mail>\n<event>"+mailEvent.getEvent()+"</event>\n<event_time>"+mailEvent.getEvent_time()+"</event_time>\n<weight>"+mailEvent.getWeight()+"</weight>\n<volume>"+mailEvent.getVolume()+"</volume>\n<time>"+mailEvent.getDuration()+"</time>\n<priority_id>"+mailEvent.getPriority_id()+"</priority_id>\n<origin>"+mailEvent.getOrigin()+"</origin>\n<destination>"+mailEvent.getDestination()+"</destination>\n<price>"+mailEvent.getPrice()+"</price>\n<cost>"+mailEvent.getCost()+"</cost>\n</mail>\ninto\ndoc(\"Kps_manager.xml\")/Business_events/mail_events";            
        this.bb(xquery);
    }
    
}
