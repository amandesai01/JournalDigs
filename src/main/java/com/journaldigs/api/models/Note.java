package com.journaldigs.api.models;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Note {
    @Id
    private String noteid;
    private String title;
    private String contents;
    private Date createdDate;

    @ManyToOne
    private Journal journal; 

    public Note(){
        super();
    }

    public Note(String title, String contents, Date createdDate, Journal j){
        this.noteid = UUID.randomUUID().toString();
        this.title = title;
        this.contents = contents;
        this.createdDate = createdDate;
        this.journal = j;
    }

    public String getNoteid() {
        return this.noteid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContents() {
        return this.contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public Date getCreatedDate() {
        return this.createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    
}
