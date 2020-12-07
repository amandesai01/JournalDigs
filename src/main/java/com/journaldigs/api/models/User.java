package com.journaldigs.api.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User{
    @Id
    private String id;
    private String name;
    private String phoneno;
    private String email;
    private String password;

    public User(){
        super();
    }

    public User(String name, String phoneno, String email, String password){
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.phoneno = phoneno;
        this.email = email;
        this.password = password;
    }

    public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
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
        return this.email;
    }

    public void setEmailid(String emailid) {
        this.email = emailid;
    }

}