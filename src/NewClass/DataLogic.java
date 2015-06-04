/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NewClass;
import domain.DiscontinueEvent;
import domain.Event;
import domain.SetComparator;
import domain.UpdateCustomerPriceEvent;
import domain.UpdateTransportPriceEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author T
 */
public class DataLogic {
    
    private ArrayList<Event> events;
    private ArrayList<UpdateTransportPriceEvent> transportPriceChanges;
    private ArrayList<UpdateCustomerPriceEvent> customerPriceChanges;
    private ArrayList<DiscontinueEvent> discontinuedEvents;
    private ArrayList<UpdateTransportPriceEvent> currentRoutes;
    private HashMap<String, ArrayList<String>> comboMap;
    
    public DataLogic(){
        this.events = new ArrayList<Event>();
        this.transportPriceChanges = new ArrayList<UpdateTransportPriceEvent>();
        this.customerPriceChanges = new ArrayList<UpdateCustomerPriceEvent>();
        this.discontinuedEvents = new ArrayList<DiscontinueEvent>();
        this.comboMap = new HashMap<String, ArrayList<String>>();
                
    }
    
    public void loadData(){
        
        this.loadCustomerPriceChanges();
        this.loadDiscontinuedData();
        this.loadTransportPriceChanges();
        this.loadCurrentRoutes();
        this.events = new ArrayList<Event>();
        this.events.addAll(transportPriceChanges);
        this.events.addAll(customerPriceChanges);
        this.events.addAll(discontinuedEvents);
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public ArrayList<UpdateTransportPriceEvent> getTransportPriceChanges() {
        if (transportPriceChanges == null) return new ArrayList<>();
        return transportPriceChanges;
    }
    public ArrayList<UpdateCustomerPriceEvent> getCustomerPriceChanges() {
        if (customerPriceChanges == null) return new ArrayList<>();
        return customerPriceChanges;
    }
    public ArrayList<DiscontinueEvent> getDiscontinuedEvents() {
        if (discontinuedEvents == null) return new ArrayList<>();
        return discontinuedEvents;
    }
    public ArrayList<UpdateTransportPriceEvent> getCurrentRoutes() {
        if (currentRoutes == null) return new ArrayList<>();
        return currentRoutes;
    }
    
    public void addNewRoute(UpdateTransportPriceEvent tEvent, UpdateCustomerPriceEvent cEvent){
        tEvent.setEvent(this.events.size() + "");
        this.events.add(tEvent);
        this.transportPriceChanges.add(tEvent);
        cEvent.setEvent(this.events.size() + "");
        this.events.add(cEvent);
        this.customerPriceChanges.add(cEvent);
        
        Insert insert = new Insert();
        try{
            insert.updateTransportPrice(tEvent);
            insert.updateCustomerPrice(cEvent);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    /**
     * Method which iterates the List of UpdateTransportPriceEvent and removes duplicates,
     * as well as UpdateTransportPriceEvents which are earlier than a related discontinueEvent.
     */
    private void loadCurrentRoutes(){
        currentRoutes = new ArrayList<UpdateTransportPriceEvent>(this.transportPriceChanges);
        Collections.sort(currentRoutes, new SetComparator());
        
        int i = currentRoutes.size() - 1;
        // Remove duplicates
        while (i > 0){
            UpdateTransportPriceEvent firstRoute = currentRoutes.get(i);
            int j = i - 1;
            while (j >= 0){
                UpdateTransportPriceEvent r = currentRoutes.get(j);
                if (r.getOrigin().equals(firstRoute.getOrigin()) && 
                        r.getDestination().equals(firstRoute.getDestination()) && 
                            r.getPriority_id().equals(firstRoute.getPriority_id()) &&
                                r.getCompany().equals(firstRoute.getCompany()) &&
                                    r != firstRoute){
                    currentRoutes.remove(r);
                }
                --j;
            }
            --i;
        }
        // Now remove the routes which have discontinueEvents after it
        for (int k = 0; k != this.discontinuedEvents.size(); ++k) {
            DiscontinueEvent e = discontinuedEvents.get(k);
            i = currentRoutes.size() - 1;
            while (i >= 0) {
                UpdateTransportPriceEvent firstRoute = currentRoutes.get(i);
                if (e.getOrigin().equals(firstRoute.getOrigin())
                        && e.getDestination().equals(firstRoute.getDestination())
                            && e.getPriority_id().equals(firstRoute.getPriority_id())
                                && e.getCompany().equals(firstRoute.getCompany()) &&
                                    e.getEvent_time() > firstRoute.getEvent_time()) {
                    currentRoutes.remove(i);
                }
                --i;
            }
        }
        
    }
    
    private void loadComboMap(){
        this.comboMap = new HashMap<String, ArrayList<String>>();
        
        
    }
    
    
    private void loadTransportPriceChanges(){
        this.transportPriceChanges = new Query().updateTransportList();
        Collections.sort(this.transportPriceChanges, new SetComparator());
    }
    
    private void loadCustomerPriceChanges(){
        this.customerPriceChanges = new Query().updateCustomerPriceEventList();
        Collections.sort(this.customerPriceChanges, new SetComparator());
    }
    
    private void loadDiscontinuedData(){
        this.discontinuedEvents = new Query().discontinuelist();
        Collections.sort(this.discontinuedEvents, new SetComparator());
    }
}