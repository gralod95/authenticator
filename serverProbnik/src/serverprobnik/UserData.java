/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package serverprobnik;

/**
 *
 * @author macbook
 */
public class UserData {
    private String passwordHash;
    private String solt;
    
    UserData(String pasHash,String soltIn){
        solt = soltIn;
        passwordHash = pasHash;
    }
    public String getPasswordHash(){
       return passwordHash;
    }
    public String getSalt(){
       return solt;
    }
    public void setPasswordHash(String new_passwordHash){
        passwordHash = new_passwordHash;
    }
    public void setSalt(String new_solt){
        solt = new_solt;
    }
}
