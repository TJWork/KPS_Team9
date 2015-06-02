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

public class Query {

    private final static String update_customer_price_event = "for $c in fn:doc('Kps_manager.xml')/Business_events/ChangeCustomerPrice_events/price[event <= 100]\n" + "return\n" + "<a>{$c/event/text()},{$c/event_time/text()},{$c/priority_id/text()},{$c/origin/text()},{$c/destination/text()},{$c/company/text()},{$c/customer_cost_pergram/text()},{$c/customer_cost_percc/text()}</a>";
    private final static String update_transport_price_event = "for $c in fn:doc('Kps_manager.xml')/Business_events/ChangeTransportPrice/price[event <= 100]\n" + "return\n" + "<a>{$c/event/text()},{$c/event_time/text()},{$c/priority_id/text()},{$c/origin/text()},{$c/destination/text()},{$c/company/text()},{$c/company_cost_pergram/text()},{$c/company_cost_percc/text()}</a>";
    private final static String discontinue_event = "for $c in fn:doc('Kps_manager.xml')/Business_events/DiscountinueRoute_events/dicontinue[event <= 100]\n" + "return\n" + "<a>{$c/event/text()},{$c/event_time/text()},{$c/priority_id/text()},{$c/origin/text()},{$c/destination/text()},{$c/company/text()},{$c/origin/text()},{$c/destination/text()}</a>";
    private final static String mail_event = "for $c in fn:doc('Kps_manager.xml')/Business_events/mail_events/mail[event <= 100]" + "return" + "<a>{$c/event/text()},{$c/event_time/text()},{$c/weight/text()},{$c/volume/text()},{$c/time/text()},{$c/priority_id/text()},{$c/origin/text()},{$c/destination/text()},{$c/price/text()},{$c/cost/text()}</a>";
    private final static String URI = "xmldb:exist://localhost:8080/exist/xmlrpc";
    private final static String driver = "org.exist.xmldb.DatabaseImpl";

    public List bb(String query) throws Exception {
        Class cl = Class.forName(driver);
        String[] Value2 = null;
        Database database = (Database) cl.newInstance();
        DatabaseManager.registerDatabase(database);
        Collection col = DatabaseManager.getCollection(URI + "/db/kps");
        XQueryService service = (XQueryService) col.getService("XQueryService", "1.0");
        service.setProperty("indent", "yes");
        ResourceSet result = service.query(mail_event);
        ResourceIterator i = result.getIterator();
        List list = new ArrayList();
        while (i.hasMoreResources()) {
            Resource r = i.nextResource();
            String Value = (String) r.getContent();
            Value2 = Value.substring(3, (Value.length() - 4)).split(",");
            list.add(Value2);
        }
        return list;
    }

    public List Miallist(List list) {
        String[] asd = (String[]) list.get(0);
        List list2 = new ArrayList();
        for (int h = 0; h <= list.size(); h++) {
            MailEvent mailEvent = new MailEvent();

            mailEvent.setEvent(asd[0]);
            mailEvent.setEvent_time(asd[1]);
            mailEvent.setWeight(asd[2]);
            mailEvent.setVolume(asd[3]);
            mailEvent.setTime(asd[4]);
            mailEvent.setPriority_id(asd[5]);
            mailEvent.setOrigin(asd[6]);
            mailEvent.setDestination(asd[7]);
            mailEvent.setPrice(asd[8]);
            mailEvent.setCost(asd[9]);
            //allocate
            list2.add(mailEvent);
        }
        return list2;
    }

    ;
    
    
    public List Discontinuelist(List list) {
        String[] asd = (String[]) list.get(0);
        List list2 = new ArrayList();
        for (int h = 0; h <= list.size(); h++) {
            DiscontinueEvent discontinueEvent = new DiscontinueEvent();

            discontinueEvent.setEvent(asd[0]);
            discontinueEvent.setEvent_time(asd[1]);
            discontinueEvent.setPriority_id(asd[2]);
            discontinueEvent.setOrigin(asd[3]);
            discontinueEvent.setDestination(asd[4]);
            discontinueEvent.setCompany(asd[5]);
            list2.add(discontinueEvent);
        }
        return list2;
    }

    ;
  
    public List UpdateTransportList(List list) {
        String[] asd = (String[]) list.get(0);
        List list2 = new ArrayList();
        for (int h = 0; h <= list.size(); h++) {
            UpdateTransportPriceEvent ucpe = new UpdateTransportPriceEvent();
            ucpe.setEvent(asd[0]);
            ucpe.setEvent_time(asd[1]);
            ucpe.setPriority_id(asd[2]);
            ucpe.setOrigin(asd[3]);
            ucpe.setDestination(asd[4]);
            ucpe.setCompany(asd[5]);
            ucpe.setCompany_cost_pergram(asd[6]);
            ucpe.setCompany_cost_percc(asd[7]);
            list2.add(ucpe);
        }
        return list2;
    }

    ;
    
    public List UpdateCustomerPriceEventList(List list) {
        String[] asd = (String[]) list.get(0);
        List list2 = new ArrayList();
        for (int h = 0; h <= list.size(); h++) {
            UpdateCustomerPriceEvent ucpe = new UpdateCustomerPriceEvent();
            ucpe.setEvent(asd[0]);
            ucpe.setEvent_time(asd[1]);
            ucpe.setPriority_id(asd[2]);
            ucpe.setOrigin(asd[3]);
            ucpe.setDestination(asd[4]);
            ucpe.setCompany(asd[5]);
            ucpe.setCustomer_cost_pergram(asd[6]);
            ucpe.setCustomer_cost_percc(asd[7]);
            list2.add(ucpe);
        }
        return list2;
    }
;

}
