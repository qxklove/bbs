package com.qxk.model;

public class Account {
    private String name;
    private String password;
    private String email;
    
    public Account() {}
    
    public Account(String name,String password,String email) {
    	this.name=name;
    	this.password=password;
    	this.email=email;
    }
    
    public String getName() {
    	return this.name;
    }
    
    public String getPassword() {
    	return this.password;
    }
    
    public String getEmail() {
    	return this.email;
    }
    
    public void setName(String name) {
    	this.name=name;
    }
    
    public void setPassword(String password) {
    	this.password=password;
    }
    
    public void setEmail(String email) {
    	this.email=email;
    }
}
