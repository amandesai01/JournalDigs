package main.java.com.journaldigs.api.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{
    @Id
    private String id;
    private String name;
    private String phoneno;
    private String emailid;

    public User(String name, String phoneno, String emailid){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneno = phoneno;
        this.emailid = emailid;
    }

    public String getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return this.phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getEmailid() {
        return this.emailid;
    }

    public void setEmailid(String emailid) {
        this.emailid = emailid;
    }

}