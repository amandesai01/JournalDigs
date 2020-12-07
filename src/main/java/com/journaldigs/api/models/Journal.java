package com.journaldigs.api.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Journal {
    @Id
    private String journalid;
    private String title;
    private Date createdDate;

    @ManyToOne
    private User owner;

    public Journal(String title, Date createdDate, User u){
        this.journalid = UUID.randomUUID().toString();
        this.title = title;
        this.createdDate = createdDate;
        this.owner = u;
    }

    public String getJournalid() {
        return this.journalid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }
}
