/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Kane
 */
public class UpdateTransportPriceEvent {
    private String event;
    private String event_time;
    private String priority_id;
    private String origin;
    private String destination;
    private String company;
    private String company_cost_pergram;
    private String company_cost_percc;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getEvent_time() {
        return event_time;
    }

    public void setEvent_time(String event_time) {
        this.event_time = event_time;
    }

    public String getPriority_id() {
        return priority_id;
    }

    public void setPriority_id(String priority_id) {
        this.priority_id = priority_id;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getCompany_cost_pergram() {
        return company_cost_pergram;
    }

    public void setCompany_cost_pergram(String company_cost_pergram) {
        this.company_cost_pergram = company_cost_pergram;
    }

    public String getCompany_cost_percc() {
        return company_cost_percc;
    }

    public void setCompany_cost_percc(String company_cost_percc) {
        this.company_cost_percc = company_cost_percc;
    }

    
}
