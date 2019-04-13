/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

/**
 *
 * @author jknutson
 */
public class user {
    
    public String fName=null;
    public String lName=null;
    public String uName;
    public boolean isAdmin;
    public boolean authenticated=false;
    
    public user(){
    
    }
    
    public user(String fName, String lName){
    this.fName=fName;
    this.lName=lName;
    }
    
    public user(String fName, String lName, boolean authenticated){
    this.fName=fName;
    this.lName=lName;
    this.authenticated=authenticated;
    }
    
    public user(String fName, String lName, String uName, boolean isAdmin, boolean authenticated){
    this.fName=fName;
    this.lName=lName;
    this.uName=uName;
    this.isAdmin=isAdmin;
    this.authenticated=authenticated;
    
    }
    
    public String getfName(){
    return fName;
    }
    public String getlName(){
    return lName;
    }
    public String getuName(){
    return uName;
    }
    
    public void setuName(String uName){
    this.uName=uName;
    }
    
    public void setfName(String fName){
    this.fName=fName;
    }
    
    public void setlName(String lName){
    this.lName=lName;
    }
}
