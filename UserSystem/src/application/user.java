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
    
    public user(String fName, String lName){
    this.fName=fName;
    this.lName=lName;
    }
    
    public String getfName(){
    return fName;
    }
    public String getlName(){
    return lName;
    }
    
}