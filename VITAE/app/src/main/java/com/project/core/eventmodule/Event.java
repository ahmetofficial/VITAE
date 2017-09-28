package com.project.core.eventmodule;

import com.project.core.locationmodule.Adress;
import com.project.core.usermodule.User;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Ahmet Kaymak on 26.12.2016.
 */

public class Event {

    public Event(){}
    public Event(int eventId, String eventType, int subjectId, String eventName, String eventDescription, Date eventDate, User event_owner) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.subjectId = subjectId;
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.event_owner = event_owner;
    }

    private int eventId;
    private String eventType;
    private int subjectId;
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private Date eventCreatedDate;
    private int participationCount;
    private User event_owner;
    //event foto eksik
    private double eventGeneralRate;
    private Adress eventAdress;


    private ArrayList<User> eventHaveParticipant=new ArrayList<>();
    //event post eksik

    //fields that readable and writeable
    public int getEventId() {
        return eventId;
    }
    public void setEventId(int eventId) {
        this.eventId = eventId;
    }
    public String getEventType() {
        return eventType;
    }
    public void setEventType(String eventType) {
        this.eventType = eventType;
    }
    public int getSubjectId() {
        return subjectId;
    }
    public void setSubjectId(int subjectId) {
        this.subjectId = subjectId;
    }
    public String getEventName() {
        return eventName;
    }
    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventDescription() {
        return eventDescription;
    }
    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }
    public Date getEventDate() {
        return eventDate;
    }
    public void setEventDate(Date eventDate) {
        this.eventDate = eventDate;
    }
    public Date getEventCreatedDate() {
        return eventCreatedDate;
    }
    public void setEventCreatedDate(Date eventCreatedDate) {
        this.eventCreatedDate = eventCreatedDate;
    }
    public User getEvent_owner() {
        return event_owner;
    }
    public void setEvent_owner(User event_owner) {
        this.event_owner = event_owner;
    }

    //field that just readable
    public int getParticipationCount() {
        return participationCount;
    }
    public double getEventGeneralRate() {
        return eventGeneralRate;
    }

}
